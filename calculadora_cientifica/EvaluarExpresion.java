import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
public class EvaluarExpresion{
    public static double evaluarExpresion(String expr, boolean esGrados) {
    // 1\. Si está en Grados, convertimos el argumento de las funciones trigonométricas
        double rad = Math.PI / 180.0;
        if (esGrados) {
            expr = expr.replace("sin(", "sin(" + rad + "*");
            expr = expr.replace("cos(", "cos(" + rad + "*");
            expr = expr.replace("tan(", "tan(" + rad + "*");
        }
        // 2\. Reemplazamos el símbolo π por su valor numérico
        expr = expr.replaceAll("(\\d|\\))π", "$1*π");
        expr = expr.replace("π", "(" + Math.PI + ")");
        Expression e = new ExpressionBuilder(expr).build();
        return e.evaluate();
    }
}
