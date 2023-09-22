package med.voll.api.view;

public class HelloView {
    public static String generatePage(String texto) {
        // Criação da página HTML com estilização preta, cinza e branca
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>");
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<title>Clínica Voll Med</title>");
        htmlBuilder.append("<meta charset=\"utf-8\">");
        htmlBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        htmlBuilder.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style/reset.css\">");
        htmlBuilder.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style/style.css\">");
        htmlBuilder.append("<link rel=\"icon\" href=\"/img/cruz.ico\" type=\"image/ico\">");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<div class=\"json-container\">");
        htmlBuilder.append("<h1 class=\"titulo\">");
        htmlBuilder.append("Clínica Voll Med</h1>");
        htmlBuilder.append("<div class=\"json-sub\">");
        htmlBuilder.append("<p class=\"paragrafo\">");
        htmlBuilder.append(texto);
        htmlBuilder.append("</p>");
        htmlBuilder.append("</div>");
        htmlBuilder.append(carregaJs("utils"));
        htmlBuilder.append("</div>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");
        return htmlBuilder.toString();
    }

    public static String carregaJs(String jsNome){
        return "<script src=\"/js/" + jsNome + ".js\"></script>";
    }
}
