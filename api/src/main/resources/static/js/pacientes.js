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
            console.log("caminho certo")
            console.log(data.content);
            // Limpar os resultados anteriores
            resposta.innerHTML = "";

            // Criar um objeto para armazenar os resultados por letra
            const resultadosPorLetra = {};

            // Agrupar os resultados por letra
            data.content.forEach((result) => {
            const primeiraLetra = result.nome.charAt(0).toUpperCase();
            if (!resultadosPorLetra[primeiraLetra]) {
              resultadosPorLetra[primeiraLetra] = [];
            }
            resultadosPorLetra[primeiraLetra].push(result);
            });

          // Exibir os resultados na página
            for (var letra in resultadosPorLetra) {
            const letraElement = document.createElement("div");
            letraElement.className = "containerLetra";
            const divLetra = document.createElement("div");
            divLetra.className = "letra";
            divLetra.innerHTML =
              `<p>${letra}</p>` + `<img src="../img/logo.svg" alt="logo">`;
            letraElement.appendChild(divLetra);
            resposta.appendChild(letraElement);

            resultadosPorLetra[letra].forEach((result) => {
              const resultElement = document.createElement("div");
              resultElement.className = "json";

              resultElement.addEventListener("click", function () {
                var divResult = document.getElementById("jsonResult");
                divResult.innerHTML = "";

                const divBox = document.createElement("div");
                divBox.className = "box";

                divBox.appendChild(closeDiv());

                // resultElement.appendChild(jsonItem);

                divBox.appendChild(createResult(result));

                divResult.appendChild(divBox);
              });

              resultElement.appendChild(createResult(result));

              letraElement.appendChild(resultElement);
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

// Function to format the CEP
function formatarCep(cep) {
  if (cep.length === 8) {
    return `${cep.slice(0, 2)}.${cep.slice(2, 5)}-${cep.slice(5)}`;
  }
  // Se o CEP não tiver 8 dígitos, retornar o valor original
  return cep;
}

// Function to create the json result
function createResult(result) {
  const jsonItem = document.createElement("div");
  jsonItem.className = "jsonItem";

  const pNome = document.createElement("p");
  pNome.className = "jsonNome";
  pNome.innerHTML = result.nome;
  jsonItem.appendChild(pNome);

  if (result.hasOwnProperty("cpf")) {
    console.log("tem cpf");
    const pTelefone = document.createElement("p");
    pTelefone.className = "jsonCampos";
    pTelefone.innerHTML = "Tel: " + result.telefone;
    jsonItem.appendChild(pTelefone);

    const pEmail = document.createElement("p");
    pEmail.className = "jsonCampos";
    pEmail.innerHTML = result.email;
    jsonItem.appendChild(pEmail);

    const pCpf = document.createElement("p");
    pCpf.className = "jsonCampos";

    const firstSlice = result.cpf.substring(0, 3);
    const secondSlice = result.cpf.substring(3, 6);
    const thirdSlice = result.cpf.substring(6, 9);
    const fourthSlice = result.cpf.substring(9, 11);
    const cpfFormated = firstSlice + "." + secondSlice + "." + thirdSlice + "-" + fourthSlice;

    pCpf.innerHTML = cpfFormated;
    jsonItem.appendChild(pCpf);
  } else if (result.hasOwnProperty("crm")) {
    const especCrm = document.createElement("p");
    especCrm.className = "jsonCampos";

    const prePoint = result.crm.substring(0, 2);
    const posPoint = result.crm.substring(2, 5);
    const crmFormated = prePoint + "." + posPoint;

    especCrm.innerHTML = result.especialidade + " | CRM " + crmFormated + "-" + result.endereco.uf;
    jsonItem.appendChild(especCrm);

    const email = document.createElement("p");
    email.className = "jsonCampos";
    email.innerHTML = result.email;
    jsonItem.appendChild(email);

    const telefone = document.createElement("p");
    telefone.className = "jsonCampos";
    telefone.innerHTML = "Tel: " + result.telefone;
    jsonItem.appendChild(telefone);
  }

  const endereco = document.createElement("div");
  endereco.className = "jsonEndereco";

  const pEndereco1 = document.createElement("p");
  pEndereco1.innerHTML =
    result.endereco.logradouro + ", " + result.endereco.numero;
  endereco.appendChild(pEndereco1);

  const pEndereco2 = document.createElement("p");
  pEndereco2.innerHTML = result.endereco.cidade + "/" + result.endereco.uf;
  endereco.appendChild(pEndereco2);

  const pEndereco3 = document.createElement("p");
  const cep = formatarCep(result.endereco.cep);
  pEndereco3.innerHTML = "CEP: " + cep;
  endereco.appendChild(pEndereco3);

  jsonItem.appendChild(endereco);
  return jsonItem;
}
