// Obter o nome da página atual sem a extensão .html
const currentPage = window.location.pathname
  .split("/")
  .pop()
  .replace(".html", "");
console.log(currentPage);

var resposta = document.getElementById("results");

document.addEventListener("DOMContentLoaded", function () {
  const resposta = document.getElementById("results");
  resposta.innerHTML = "Carregando...";
  console.log("DOM completamente carregado e analisado");
  doSearch();
});

// Função para fazer a pesquisa com base no tipo selecionado
function doSearch() {
  const searchTerm = "";
  console.log(searchTerm);

  // Verificar se o campo de pesquisa está vazio e o tipo de pesquisa selecionado não é vazio
  if (searchTerm === "" && currentPage !== "") {
    // Se estiver vazio, faça uma chamada à API para obter todos os resultados
    // Substitua a URL abaixo pela URL real da sua API
    const apiUrl = `http://localhost:8080/${currentPage}`;

    // Fazer a chamada à API usando fetch
    fetch(apiUrl)
      .then((response) => response.json())
      .then((data) => {
        console.log("data");
        // Verificar se 'data.content' é um array
        if (Array.isArray(data.content)) {
            console.log("é array");
            console.log(data.content);
            // Limpar os resultados anteriores
            resposta.innerHTML = "";

            // Criar um objeto para armazenar os resultados por letra
            const resultadosPorData = {};

            // Agrupar os resultados por letra
            data.content.forEach((result) => {
            const dataConsulta = result.data;
            if (!resultadosPorData[dataConsulta]) {
              resultadosPorData[dataConsulta] = [];
            }
            resultadosPorData[dataConsulta].push(result);
            });

          // Exibir os resultados na página
            for (var dataList in resultadosPorData) {
            const dataElement = document.createElement("div");
            dataElement.className = "containerLetra";
            const divData = document.createElement("div");
            divData.className = "letra";
            const dia = dataList.substring(6, 8);
            const mes = dataList.substring(4, 6);
            const ano = dataList.substring(0, 4);
            const dataFormatada = dia + "/" + mes + "/" + ano;

            divData.innerHTML =
              `<p>${dataFormatada}</p>` + `<img src="../img/logo.svg" alt="logo">`;
            dataElement.appendChild(divData);
            resposta.appendChild(dataElement);

            resultadosPorData[dataList].forEach((result) => {
                const resultElement = document.createElement("div");
                resultElement.className = "json";

                resultElement.addEventListener("click", function () {
                var divResult = document.getElementById("jsonResult");
                divResult.innerHTML = "";

                const divBox = document.createElement("div");
                divBox.className = "box";

                divBox.appendChild(closeDiv());

                divBox.appendChild(createResult(result));

                divResult.appendChild(divBox);
              });

              resultElement.appendChild(createResult(result));

              dataElement.appendChild(resultElement);
              console.log(result);
            });
          }
        } else {
          console.log("não é array");
          // Clear previous results
          resposta.innerHTML = "";

          // Create an object to store the results by letter
          const resultadosPorLetra = {};

          data.forEach((result) => {
            const primeiraLetra = result.nome.charAt(0).toUpperCase();
            if (!resultadosPorLetra[primeiraLetra]) {
              resultadosPorLetra[primeiraLetra] = [];
            }
            resultadosPorLetra[primeiraLetra].push(result);
          });

          // Exibir os resultados na página
          for (var letra in resultadosPorLetra) {
            const resultElement = document.createElement("div");
            resultElement.className = "json";

            const letraElement = document.createElement("div");
            letraElement.className = "containerLetra";
            const divLetra = document.createElement("div");
            divLetra.className = "letra";
            divLetra.innerHTML =
              `<p>${letra}</p>` + `<img src="../img/logo.svg" alt="logo">`;
            letraElement.appendChild(divLetra);
            resposta.appendChild(letraElement);

            resultadosPorLetra[letra].forEach((result) => {
              console.log("else");
              resultElement.addEventListener("click", function () {
                var divResult = document.getElementById("jsonResult");
                divResult.innerHTML = "";

                const divBox = document.createElement("div");
                divBox.className = "box";

                divBox.appendChild(closeDiv());
                divBox.appendChild(createResult(result));

                divResult.appendChild(divBox);
              });

              resultElement.appendChild(createResult(result));

              letraElement.appendChild(resultElement);
              console.log(result);
            });
          }
        }
      });
  }
}

// Function to close the div
function fechaDiv() {
  var divResult = document.getElementById("jsonResult");
  divResult.innerHTML = "";
}

// Function to create the close button
function closeDiv() {
  const fechar = document.createElement("input");
  fechar.className = "fechar";
  fechar.type = "button";
  fechar.value = "X";
  fechar.onclick = fechaDiv;
  return fechar;
}

// Function to create the json result
function createResult(result) {
    const jsonItem = document.createElement("div");
    jsonItem.className = "jsonItem";

    const cHora = document.createElement("p");
    cHora.className = "jsonTitulo";
    const hora = result.hora.substring(0, 2);
    const minutos = result.hora.substring(3, 5);
    const dataFormatada = hora + "h" + minutos;
    cHora.innerHTML = dataFormatada;
    jsonItem.appendChild(cHora);

    const divMedico = document.createElement("div");
    divMedico.className = "divMedico";
        const pNomeMedico = document.createElement("p");
        pNomeMedico.className = "jsonCamposN";
        pNomeMedico.innerHTML = result.nomeMedico;
        divMedico.appendChild(pNomeMedico);

        const especCrm = document.createElement("p");
        especCrm.className = "jsonCampos";

        const prePoint = result.crm.substring(0, 2);
        const posPoint = result.crm.substring(2, 5);
        const crmFormated = prePoint + "." + posPoint;

        especCrm.innerHTML = result.especialidade + " | CRM " + crmFormated + "-" + result.uf;
        divMedico.appendChild(especCrm);

        jsonItem.appendChild(divMedico);

    const divPaciente = document.createElement("div");
    divPaciente.className = "divPaciente";
        const pNomePaciente = document.createElement("p");
        pNomePaciente.className = "jsonCamposN";
        pNomePaciente.innerHTML = result.nomePaciente;
        divPaciente.appendChild(pNomePaciente);

        const pTipoPaciente = document.createElement("p");
        pTipoPaciente.className = "jsonCampos";
        pTipoPaciente.innerHTML = result.tipoPaciente;
        divPaciente.appendChild(pTipoPaciente);

    jsonItem.appendChild(divPaciente);

    
    return jsonItem;
}
