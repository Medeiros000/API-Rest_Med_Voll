package med.voll.api.view;

public class HelloView {
    public static String generatePage(String texto) {
        // Criação da página HTML com estilização preta e branca
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>");
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<title>Clínica Voll Med</title>");
        htmlBuilder.append("<meta charset=\"utf-8\">");
        htmlBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        htmlBuilder.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/reset.css\">");
        htmlBuilder.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/style.css\">");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body style=\"color: black; background-color: grey; display: flex; flex-direction: column; align-content: center;\">");
        htmlBuilder.append("<h1 style=\" align-self: center; font-family: Arial, sans-serif; font-size: 48px font-weight: bold;\">");
        htmlBuilder.append("Clínica Voll Med</h1>");
        htmlBuilder.append("<p style=\" align-self: center; font-family: Arial, sans-serif; font-size: 32px font-weight: bold;\">");
        htmlBuilder.append(texto);
        htmlBuilder.append("</p>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }
}
