const resposta = document.getElementById("results");

document.addEventListener("DOMContentLoaded", function () {
  resposta.innerHTML = "Carregando...";
  console.log("DOM completamente carregado e analisado");
  getMedicos();
});

function getMedicos() {
  var resposta = document.getElementById("results");
  fetch("http://localhost:8080/medicos")
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      console.log("data");
      // Verificar se 'data.content' é um array
      if (Array.isArray(data.content)) {
        console.log("é array");
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
          letraElement.className = "json-sub";
          letraElement.innerHTML = `<h2>${letra}</h2>`;
          resposta.appendChild(letraElement);
          resultadosPorLetra[letra].forEach((result) => {
            const resultElement = document.createElement("div");
            resultElement.className = "json-sub";
            for (var key in result) {
              const p = document.createElement("p");
              p.className = "json-key";
              p.innerHTML = `<span class="json-key">${key}:</span> <span class="json-value">${result[key]}</span>`;
              resultElement.appendChild(p);
            }
            letraElement.appendChild(resultElement);
            console.log(result);
          });
        }
      } else {
        console.log("não é array");
        // Limpar os resultados anteriores
        resposta.innerHTML = "";

        // Criar um objeto para armazenar os resultados por letra
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

              const divBox = document.createElement("div");
              divBox.className = "box";
              const fechar = document.createElement("input");
              fechar.className = "fechar";
              fechar.type = "button";
              fechar.value = "X";
              fechar.onclick = fechaDiv;
              divBox.appendChild(fechar);

              const teste = document.createElement("div");
              teste.className = "teste";
              teste.innerHTML =
                result.id +
                " - " +
                result.nome +
                " - " +
                result.especialidade +
                " - " +
                result.cpf +
                " - " +
                result.email +
                " - " +
                result.telefone +
                " - " +
                result.endereco.logradouro +
                " - " +
                result.endereco.numero +
                " - " +
                result.endereco.cidade +
                " - " +
                result.endereco.uf +
                " - " +
                result.endereco.cep;
              divBox.appendChild(teste);

              divResult.appendChild(divBox);
            });

            const jsonItem = document.createElement("div");
            jsonItem.className = "jsonItem";

            const pNome = document.createElement("p");
            pNome.className = "jsonNome";
            pNome.innerHTML = result.nome;
            jsonItem.appendChild(pNome);

            const especCrm = document.createElement("p");
            especCrm.className = "jsonCampos";
            especCrm.innerHTML = result.especialidade + " | CRM " + result.crm;
            jsonItem.appendChild(especCrm);

            const email = document.createElement("p");
            email.className = "jsonCampos";
            email.innerHTML = result.email;
            jsonItem.appendChild(email);

            const telefone = document.createElement("p");
            telefone.className = "jsonCampos";
            telefone.innerHTML = "Tel: " + result.telefone;
            jsonItem.appendChild(telefone);

            const endereco = document.createElement("div");
            endereco.className = "jsonEndereco";

            const pEndereco1 = document.createElement("p");
            pEndereco1.innerHTML =
              result.endereco.logradouro + ", " + result.endereco.numero;
            endereco.appendChild(pEndereco1);

            const pEndereco2 = document.createElement("p");
            pEndereco2.innerHTML =
              result.endereco.cidade + "/" + result.endereco.uf;
            endereco.appendChild(pEndereco2);

            const pEndereco3 = document.createElement("p");
            pEndereco3.innerHTML = "CEP: " + result.endereco.cep;
            endereco.appendChild(pEndereco3);

            jsonItem.appendChild(endereco);

            resultElement.appendChild(jsonItem);

            letraElement.appendChild(resultElement);
            console.log(result);
          });
        }
      }
    });
}

function fechaDiv() {
  var divResult = document.getElementById("jsonResult");
  divResult.innerHTML = "";
}
