import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
public class EvaluarExpresion{
    public double evaluarExpresion(String expr, boolean esGrados) {
    // 1\. Si está en Grados, convertimos el argumento de las funciones trigonométricas
        if (esGrados) {
            expr = expr.replace("sin(", "sin((" + Math.PI + "/180)\*");
            expr = expr.replace("cos(", "cos((" + Math.PI + "/180)\*");
            expr = expr.replace("tan(", "tan((" + Math.PI + "/180)\*");
        } // 2\. Reemplazamos el símbolo π por su valor numérico
        expr = expr.replace("π", String.valueOf(Math.PI));
        // 3\. Evaluamos de forma directa con exp4j sin clases extra
        Expression e = new ExpressionBuilder(expr).build();
        return e.evaluate();
    }
}
