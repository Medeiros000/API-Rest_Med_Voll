// URL fixa da API
const API_BASE_URL = "https://api-rest-med-voll.onrender.com";

const resposta = $("#results");
$(function () {
  resposta.html("Carregando...");
  let searchTerm = $("#idDaCaixaDeTexto").val(); // Substitua 'idDaCaixaDeTexto' pelo ID real da sua caixa de texto

  doSearch();
  $("#search").on("input", function () {
    searchTerm = $(this).val();
    console.log(searchTerm);
    doSearch(searchTerm);
  });
});

// Obter o nome da página atual sem a extensão .html
const currentPage = window.location.pathname.split("/").pop().replace(".html", "");
console.log(currentPage);

// Função para fazer a pesquisa com base no tipo selecionado
function doSearch(searchTerm) {
  // let searchTerm = "";
  // console.log(searchTerm);

  let apiUrl = "";
  // Verificar se o campo de pesquisa está vazio e o tipo de pesquisa selecionado não é vazio
  if (searchTerm == null || searchTerm === "") {
    // Se estiver vazio, faça uma chamada à API para obter todos os resultados
    // Substitua a URL abaixo pela URL real da sua API
    apiUrl = `${API_BASE_URL}/${currentPage}`;
  } else {
    // Se não estiver vazio, faça uma chamada à API para obter os resultados filtrados
    apiUrl = `${API_BASE_URL}/${currentPage}/${searchTerm}`;
  }
  // console.log(apiUrl);
  // Fazer a chamada à API usando fetch
  $.getJSON(apiUrl, function (data) {
    // console.log(data);
    // Limpar os resultados anteriores
    resposta.html("");
    if (currentPage === "consultas") {
      const resultadosPorData = {};
      // Agrupar os resultados por data
      $.each(data.content, function (index, result) {
        const dataConsulta = result.data;
        if (!resultadosPorData[dataConsulta]) {
          resultadosPorData[dataConsulta] = [];
        }
        resultadosPorData[dataConsulta].push(result);
      });
      let i = 0;
      // Exibir os resultados na página
      $.each(resultadosPorData, function (dataList, resultados) {
        let dataElement = $("<div>").addClass("containerData py-3");
        let divData = $("<div>").addClass("letra py-3");

        divData.html(
          `<p style="color: #339CFF; font-size: 1.125rem; font-weight: 600;" class="d-inline mx-auto">${formatarData(
            dataList
          )}</p>` + `<img class="d-inline mx-auto" src="../img/logo.svg" alt="logo">`
        );
        dataElement.append(divData);
        resposta.append(dataElement);

        let resultElement = $("<div>").attr("id", "accordionExample").addClass("accordion");

        $.each(resultados, function (index, result) {
          resultElement.html(criarConsulta(result, i));
          i++;
        });
        dataElement.append(resultElement);
      });
      return;
    }

    // Criar um objeto para armazenar os resultados por letra
    let resultadosPorLetra = {};

    // Agrupar os resultados por letra
    $.each(data.content, function (index, result) {
      let primeiraLetra = result.nome.charAt(0).toUpperCase();
      if (!resultadosPorLetra[primeiraLetra]) {
        resultadosPorLetra[primeiraLetra] = [];
      }
      resultadosPorLetra[primeiraLetra].push(result);
    });
    let i = 0;
    // Exibir os resultados na página
    $.each(resultadosPorLetra, function (letra, resultados) {
      let letraElement = $("<div>").addClass("containerLetra py-3");
      let divLetra = $("<div>").addClass("letra py-3");
      divLetra.html(
        `<p style="color: #339CFF; font-size: 1.125rem; font-weight: 600;" class="d-inline mx-auto">${letra}</p>` +
        `<img class="d-inline mx-auto" src="../img/logo.svg" alt="logo">`
      );
      letraElement.append(divLetra);
      resposta.append(letraElement);

      let resultElement = $("<div>").attr("id", "accordionExample").addClass("accordion");

      $.each(resultados, function (index, result) {
        if (currentPage === "medicos") {
          resultElement.html(criarMedico(result, i));
          i++;
        } else if (currentPage === "pacientes") {
          resultElement.html(criarPaciente(result, i));
          i++;
        } else {
          resultElement.html("Erro ao carregar resultados");
        }
      });
      letraElement.append(resultElement);
    });
  });
}

function formatarCep(cep) {
  if (cep.length === 8) {
    return `${cep.slice(0, 2)}.${cep.slice(2, 5)}-${cep.slice(5)}`;
  }
  // Se o CEP não tiver 8 dígitos, retornar o valor original.
  return cep;
}

function formatarData(data) {
  if (data.length === 8) {
    return `${data.slice(6)}/${data.slice(4, 6)}/${data.slice(0, 4)}`;
  }
  // Se o data não tiver 8 dígitos, retornar o valor original.
  return data;
}

function formatarTelefone(telefone) {
  if (telefone.length === 10) {
    return `Tel: (${telefone.slice(0, 2)}) ${telefone.slice(2, 6)}-${telefone.slice(6, 10)}`;
  } else if (telefone.length === 11) {
    return `Tel: (${telefone.slice(0, 2)}) ${telefone.slice(2, 3)}.${telefone.slice(3, 7)}-${telefone.slice(7, 11)}`;
  } else {
    // Se o telefone não tiver 10 ou 11 dígitos, retornar o valor original.
    return `Tel: ${telefone}`;
  }
}

function formatarCpf(cpf) {
  if (cpf.length === 11) {
    return `Cpf: ${cpf.slice(0, 3)}.${cpf.slice(3, 6)}.${cpf.slice(6, 9)}-${cpf.slice(9, 11)}`;
  } else {
    // Se o cpf não tiver 10 ou 11 dígitos, retornar o valor original.
    return `Cpf: ${cpf}`;
  }
}

function criarMedico(result, i) {
  return `
          <div class="accordion-item border-0">
              <h2 class="accordion-header border border-0" id="heading">
                  <button class="accordion-button collapsed py-0 extras bg-white" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="false" aria-controls="collapse${i}">
                      <div class="d-inline-block">
                          <p class="">${result.nome}</p>
                          <p class="">${result.especialidade} | CRM ${result.crm}</p>
                      </div>
                  </button>
              </h2>
              <div id="collapse${i}" class="accordion-collapse collapse border-0" aria-labelledby="heading" data-bs-parent="#accordionExample">
                  <div class="accordion-body d-inline-block extras py-0">
                      <p>${result.email}</p>
                      <p>${formatarTelefone(result.telefone)}</p>
                      <p>${result.endereco.logradouro}</p>
                      <p>CEP: ${formatarCep(result.endereco.cep)}</p>
                      </div>
                      <div class="d-flex flex-row mx-3 text-center">
                          <span class="me-1 col-6 btnper">Editar</span>
                          <span class="ms-1 col-6 btnper">Desativar perfil</span>
                      </div>
              </div>
          </div>
          `;
}

function criarPaciente(result, i) {
  return `
          <div class="accordion-item border-0">
              <h2 class="accordion-header border border-0" id="heading">
                  <button class="accordion-button collapsed py-0 extras bg-white" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="false" aria-controls="collapse${i}">
                      <div class="d-inline-block">
                          <p class="">${result.nome}</p>
                          <p class="">${formatarCpf(result.cpf)}</p>
                      </div>
                      </button>
              </h2>
              <div id="collapse${i}" class="accordion-collapse collapse border-0" aria-labelledby="heading" data-bs-parent="#accordionExample">
                  <div class="accordion-body d-inline-block extras py-0">
                      <p>${result.email}</p>
                      <p>${formatarTelefone(result.telefone)}</p>
                      <p>${result.endereco.logradouro}</p>
                      <p>CEP: ${formatarCep(result.endereco.cep)}</p>
                  </div>
                  <div class="d-flex flex-row mx-3 text-center">
                      <span class="me-1 col-6 btnper">Editar</span>
                      <span class="ms-1 col-6 btnper">Desativar perfil</span>
                  </div>
              </div>
          </div>
          `;
}

function criarConsulta(result, i) {
  return `
          <div class="accordion-item border-0">
              <h2 class="accordion-header border border-0" id="heading">
                  <button class="accordion-button collapsed py-0 extras bg-white" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="false" aria-controls="collapse${i}">
                      <div class="d-inline-block consultas">
                          <p class="">${result.hora}</p>
                          <p class="">${result.nomeMedico}</p>
                          <p class="">${result.especialidade} | CRM ${result.crm}</p>
                          <p class="">${result.nomePaciente}</p>
                          <p class="">${result.tipoPaciente}</p>
                      </div>
                  </button>
              </h2>
              <div id="collapse${i}" class="accordion-collapse collapse border-0" aria-labelledby="heading" data-bs-parent="#accordionExample">
                  <div class="accordion-body d-flex flex-row justify-content-between text-center">
                      <span class="me-1 col-6 btnper">Editar</span>
                      <span class="ms-1 col-6 btnper">Cancelar consulta</span>
                  </div>
              </div>
          </div>
          `;
}
