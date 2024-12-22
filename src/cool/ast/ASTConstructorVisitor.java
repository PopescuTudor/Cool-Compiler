package cool.ast;

//import cool.ast.*;
import cool.parser.*;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class ASTConstructorVisitor extends CoolParserBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        List<ClassNode> classes = new ArrayList<>();
        for (CoolParser.ClassContext classCtx : ctx.class_()) {
            classes.add((ClassNode) visit(classCtx));
        }
        return new Program(ctx, ctx.start, classes);
    }

    @Override
    public ASTNode visitClass(CoolParser.ClassContext ctx) {
        Token name = ctx.TYPE_ID(0).getSymbol();
        Token parent = ctx.TYPE_ID().size() > 1 ? ctx.TYPE_ID(1).getSymbol() : null;
        List<Feature> features = new ArrayList<>();
        for (CoolParser.FeatureContext featureCtx : ctx.feature()) {
            features.add((Feature) visit(featureCtx));
        }
        return new ClassNode(ctx, ctx.start, name, parent, features);
    }

    @Override
    public ASTNode visitAttribute(CoolParser.AttributeContext ctx) {
        Token name = ctx.OBJECT_ID().getSymbol();
        Token type = ctx.TYPE_ID().getSymbol();
        Expression init = ctx.expr() != null ? (Expression) visit(ctx.expr()) : null;
        return new Attribute(ctx, ctx.start, name, type, init);
    }

    @Override
    public ASTNode visitMethod(CoolParser.MethodContext ctx) {
        Token name = ctx.OBJECT_ID().getSymbol();
        List<Formal> formals = new ArrayList<>();
        for (CoolParser.FormalContext formalCtx : ctx.formal()) {
            formals.add((Formal) visit(formalCtx));
        }
        Token returnType = ctx.TYPE_ID().getSymbol();
        Expression body = (Expression) visit(ctx.expr());
        return new Method(ctx, ctx.start, name, formals, returnType, body);
    }

    @Override
    public ASTNode visitFormal(CoolParser.FormalContext ctx) {
        Token name = ctx.OBJECT_ID().getSymbol();
        Token type = ctx.TYPE_ID().getSymbol();
        return new Formal(ctx, ctx.start, name, type);
    }

    @Override
    public ASTNode visitIf(CoolParser.IfContext ctx) {
        Expression condition = (Expression) visit(ctx.expr(0));
        Expression thenBranch = (Expression) visit(ctx.expr(1));
        Expression elseBranch = (Expression) visit(ctx.expr(2));
        return new If(ctx, ctx.start, condition, thenBranch, elseBranch);
    }

    @Override
    public ASTNode visitComparison(CoolParser.ComparisonContext ctx) {
        Expression left = (Expression) visit(ctx.expr(0));
        Expression right = (Expression) visit(ctx.expr(1));
        String op = ctx.getChild(1).getText();
        return new BinaryOp(ctx, ctx.start, op, left, right);
    }

    @Override
    public ASTNode visitWhile(CoolParser.WhileContext ctx) {
        Expression condition = (Expression) visit(ctx.expr(0));
        Expression body = (Expression) visit(ctx.expr(1));
        return new While(ctx, ctx.start, condition, body);
    }

    @Override
    public ASTNode visitLet(CoolParser.LetContext ctx) {
        List<LocalVarDef> declarations = new ArrayList<>();
        for (CoolParser.Let_declContext declCtx : ctx.let_decl()) {
            declarations.add((LocalVarDef) visit(declCtx));
        }
        Expression body = (Expression) visit(ctx.expr());
        return new Let(ctx, ctx.start, declarations, body);
    }

    @Override
    public ASTNode visitLetDecl(CoolParser.LetDeclContext ctx) {
        String name = ctx.OBJECT_ID().getText();
        String type = ctx.TYPE_ID().getText();
        Expression init = ctx.expr() != null ? (Expression) visit(ctx.expr()) : null;
        return new LocalVarDef(ctx, ctx.start, name, type, init);
    }

    @Override
    public ASTNode visitCase(CoolParser.CaseContext ctx) {
        Expression expr = (Expression) visit(ctx.expr(0));
        List<CaseBranch> branches = new ArrayList<>();
        for (int i = 0; i < ctx.OBJECT_ID().size(); i++) {
            String name = ctx.OBJECT_ID(i).getText();
            String type = ctx.TYPE_ID(i).getText();
            Expression branchExpr = (Expression) visit(ctx.expr(i + 1));
            branches.add(new CaseBranch(ctx, ctx.start, name, type, branchExpr));
        }
        return new Case(ctx, ctx.start, expr, branches);
    }

    @Override
    public ASTNode visitBlock(CoolParser.BlockContext ctx) {
        List<Expression> expressions = new ArrayList<>();
        for (CoolParser.ExprContext exprCtx : ctx.expr()) {
            expressions.add((Expression) visit(exprCtx));
        }
        return new Block(ctx, ctx.start, expressions);
    }

    @Override
    public ASTNode visitDispatch(CoolParser.DispatchContext ctx) {
        Expression obj = (Expression) visit(ctx.expr(0));
        String methodName = ctx.OBJECT_ID().getText();
        List<Expression> args = new ArrayList<>();
        for (int i = 1; i < ctx.expr().size(); i++) {
            args.add((Expression) visit(ctx.expr(i)));
        }
        return new Dispatch(ctx, ctx.start, obj, ctx.TYPE_ID() != null ? ctx.TYPE_ID().getText() : null, methodName, args);
    }

    @Override
    public ASTNode visitImplicitDispatch(CoolParser.ImplicitDispatchContext ctx) {
        String methodName = ctx.OBJECT_ID().getText();
        List<Expression> args = new ArrayList<>();
        for (CoolParser.ExprContext exprCtx : ctx.expr()) {
            args.add((Expression) visit(exprCtx));
        }
        return new ImplicitDispatch(ctx, ctx.start, methodName, args);
    }

    @Override
    public ASTNode visitNew(CoolParser.NewContext ctx) {
        return new New(ctx, ctx.start, ctx.TYPE_ID().getText());
    }

    @Override
    public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
        return new IsVoid(ctx, ctx.start, (Expression) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitBinaryOp(CoolParser.BinaryOpContext ctx) {
        Expression left = (Expression) visit(ctx.expr(0));
        Expression right = (Expression) visit(ctx.expr(1));
        String op = ctx.getChild(1).getText();
        return new BinaryOp(ctx, ctx.start, op, left, right);
    }

    @Override
    public ASTNode visitUnaryOp(CoolParser.UnaryOpContext ctx) {
        Expression expr = (Expression) visit(ctx.expr());
        String op = ctx.getChild(0).getText();
        return new UnaryOp(ctx, ctx.start, op, expr);
    }

    @Override
    public ASTNode visitParen(CoolParser.ParenContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public ASTNode visitId(CoolParser.IdContext ctx) {
        return new Id(ctx, ctx.start, ctx.OBJECT_ID().getText());
    }

    @Override
    public ASTNode visitInt(CoolParser.IntContext ctx) {
        return new IntConstant(ctx, ctx.start, Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public ASTNode visitString(CoolParser.StringContext ctx) {
        return new StringConstant(ctx, ctx.start, ctx.STRING().getText());
    }

    @Override
    public ASTNode visitBool(CoolParser.BoolContext ctx) {
        return new BoolConstant(ctx, ctx.start, Boolean.parseBoolean(ctx.getText()));
    }

    @Override
    public ASTNode visitAssign(CoolParser.AssignContext ctx) {
        String id = ctx.OBJECT_ID().getText();
        Expression expr = (Expression) visit(ctx.expr());
        return new Assign(ctx, ctx.start, id, expr);
    }
}