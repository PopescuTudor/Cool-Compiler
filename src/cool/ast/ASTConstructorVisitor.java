package cool.ast;

//import cool.ast.*;
import cool.parser.*;

import java.util.ArrayList;
import java.util.List;

public class ASTConstructorVisitor extends CoolParserBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        List<ClassNode> classes = new ArrayList<>();
        for (CoolParser.ClassContext classCtx : ctx.class_()) {
            classes.add((ClassNode) visit(classCtx));
        }
        return new Program(classes);
    }

    @Override
    public ASTNode visitClass(CoolParser.ClassContext ctx) {
        String name = ctx.TYPE_ID(0).getText();
        String parent = ctx.TYPE_ID().size() > 1 ? ctx.TYPE_ID(1).getText() : null;
        List<Feature> features = new ArrayList<>();
        for (CoolParser.FeatureContext featureCtx : ctx.feature()) {
            features.add((Feature) visit(featureCtx));
        }
        return new ClassNode(name, parent, features);
    }

    @Override
    public ASTNode visitAttribute(CoolParser.AttributeContext ctx) {
        String name = ctx.OBJECT_ID().getText();
        String type = ctx.TYPE_ID().getText();
        Expression init = ctx.expr() != null ? (Expression) visit(ctx.expr()) : null;
        return new Attribute(name, type, init);
    }

    @Override
    public ASTNode visitMethod(CoolParser.MethodContext ctx) {
        String name = ctx.OBJECT_ID().getText();
        List<Formal> formals = new ArrayList<>();
        for (CoolParser.FormalContext formalCtx : ctx.formal()) {
            formals.add((Formal) visit(formalCtx));
        }
        String returnType = ctx.TYPE_ID().getText();
        Expression body = (Expression) visit(ctx.expr());
        return new Method(name, formals, returnType, body);
    }

    @Override
    public ASTNode visitFormal(CoolParser.FormalContext ctx) {
        String name = ctx.OBJECT_ID().getText();
        String type = ctx.TYPE_ID().getText();
        return new Formal(name, type);
    }

    @Override
    public ASTNode visitIf(CoolParser.IfContext ctx) {
        Expression condition = (Expression) visit(ctx.expr(0));
        Expression thenBranch = (Expression) visit(ctx.expr(1));
        Expression elseBranch = (Expression) visit(ctx.expr(2));
        return new If(condition, thenBranch, elseBranch);
    }

    @Override
    public ASTNode visitComparison(CoolParser.ComparisonContext ctx) {
        Expression left = (Expression) visit(ctx.expr(0));
        Expression right = (Expression) visit(ctx.expr(1));
        String op = ctx.getChild(1).getText();
        return new BinaryOp(op, left, right);
    }

    @Override
    public ASTNode visitWhile(CoolParser.WhileContext ctx) {
        Expression condition = (Expression) visit(ctx.expr(0));
        Expression body = (Expression) visit(ctx.expr(1));
        return new While(condition, body);
    }

    @Override
    public ASTNode visitLet(CoolParser.LetContext ctx) {
        List<LocalVarDef> localVarDefs = new ArrayList<>();
        for (int i = 0; i < ctx.OBJECT_ID().size(); i++) {
            String name = ctx.OBJECT_ID(i).getText();
            String type = ctx.TYPE_ID(i).getText();
            Expression init = ctx.expr(i) != null ? (Expression) visit(ctx.expr(i)) : null;
            localVarDefs.add(new LocalVarDef(name, type, init));
        }
        Expression body = (Expression) visit(ctx.expr(ctx.expr().size() - 1));
        return new Let(localVarDefs, body);
    }

    @Override
    public ASTNode visitCase(CoolParser.CaseContext ctx) {
        Expression expr = (Expression) visit(ctx.expr(0));
        List<CaseBranch> branches = new ArrayList<>();
        for (int i = 0; i < ctx.OBJECT_ID().size(); i++) {
            String name = ctx.OBJECT_ID(i).getText();
            String type = ctx.TYPE_ID(i).getText();
            Expression branchExpr = (Expression) visit(ctx.expr(i + 1));
            branches.add(new CaseBranch(name, type, branchExpr));
        }
        return new Case(expr, branches);
    }

    @Override
    public ASTNode visitBlock(CoolParser.BlockContext ctx) {
        List<Expression> expressions = new ArrayList<>();
        for (CoolParser.ExprContext exprCtx : ctx.expr()) {
            expressions.add((Expression) visit(exprCtx));
        }
        return new Block(expressions);
    }

    @Override
    public ASTNode visitDispatch(CoolParser.DispatchContext ctx) {
        Expression obj = (Expression) visit(ctx.expr(0));
        String methodName = ctx.OBJECT_ID().getText();
        List<Expression> args = new ArrayList<>();
        for (int i = 1; i < ctx.expr().size(); i++) {
            args.add((Expression) visit(ctx.expr(i)));
        }
        return new Dispatch(obj, ctx.TYPE_ID() != null ? ctx.TYPE_ID().getText() : null, methodName, args);
    }

    @Override
    public ASTNode visitImplicitDispatch(CoolParser.ImplicitDispatchContext ctx) {
        String methodName = ctx.OBJECT_ID().getText();
        List<Expression> args = new ArrayList<>();
        for (CoolParser.ExprContext exprCtx : ctx.expr()) {
            args.add((Expression) visit(exprCtx));
        }
        return new ImplicitDispatch(methodName, args);
    }

    @Override
    public ASTNode visitNew(CoolParser.NewContext ctx) {
        return new New(ctx.TYPE_ID().getText());
    }

    @Override
    public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
        return new IsVoid((Expression) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitBinaryOp(CoolParser.BinaryOpContext ctx) {
        Expression left = (Expression) visit(ctx.expr(0));
        Expression right = (Expression) visit(ctx.expr(1));
        String op = ctx.getChild(1).getText();
        return new BinaryOp(op, left, right);
    }

    @Override
    public ASTNode visitUnaryOp(CoolParser.UnaryOpContext ctx) {
        Expression expr = (Expression) visit(ctx.expr());
        String op = ctx.getChild(0).getText();
        return new UnaryOp(op, expr);
    }

    @Override
    public ASTNode visitParen(CoolParser.ParenContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public ASTNode visitId(CoolParser.IdContext ctx) {
        return new Id(ctx.OBJECT_ID().getText());
    }

    @Override
    public ASTNode visitInt(CoolParser.IntContext ctx) {
        return new IntConstant(Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public ASTNode visitString(CoolParser.StringContext ctx) {
        return new StringConstant(ctx.STRING().getText());
    }

    @Override
    public ASTNode visitBool(CoolParser.BoolContext ctx) {
        return new BoolConstant(Boolean.parseBoolean(ctx.getText()));
    }

    @Override
    public ASTNode visitAssign(CoolParser.AssignContext ctx) {
        String id = ctx.OBJECT_ID().getText();
        Expression expr = (Expression) visit(ctx.expr());
        return new Assign(id, expr);
    }
}