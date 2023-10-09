const resposta = document.getElementById("results");

document.addEventListener("DOMContentLoaded", function() {
    resposta.innerHTML = "Carregando...";
    console.log("DOM completamente carregado e analisado");
    getMedicos();
});

function getMedicos() {
    var resposta = document.getElementById("results");
    fetch("http://localhost:8080/medicos")
        .then(response => response.json())
        .then(data => {
            console.log(data);
            console.log("data");
            // Verificar se 'data.content' é um array
            if (Array.isArray(data.content)) {
                console.log("é array");
                console.log(data.content);
                // Limpar os resultados anteriores
                resposta.innerHTML = '';

                // Criar um objeto para armazenar os resultados por letra
                const resultadosPorLetra = {};

                // Agrupar os resultados por letra
                data.content.forEach(result => {
                    const primeiraLetra = result.nome.charAt(0).toUpperCase();
                    if (!resultadosPorLetra[primeiraLetra]) {
                        resultadosPorLetra[primeiraLetra] = [];
                    }
                    resultadosPorLetra[primeiraLetra].push(result);
                });

                // Exibir os resultados na página
                for (var letra in resultadosPorLetra) {
                    const letraElement = document.createElement('div');
                    letraElement.className = "json-sub";
                    letraElement.innerHTML = `<h2>${letra}</h2>`;
                    resposta.appendChild(letraElement);
                    resultadosPorLetra[letra].forEach(result => {
                        const resultElement = document.createElement('div');
                        resultElement.className = "json-sub";
                        for (var key in result ) {
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
                resposta.innerHTML = '';

                // Criar um objeto para armazenar os resultados por letra
                const resultadosPorLetra = {};

                data.forEach(result => {
                    const primeiraLetra = result.nome.charAt(0).toUpperCase();
                    if (!resultadosPorLetra[primeiraLetra]) {
                        resultadosPorLetra[primeiraLetra] = [];
                    }
                    resultadosPorLetra[primeiraLetra].push(result);
                });

                // Exibir os resultados na página
                for (var letra in resultadosPorLetra) {
                    const letraElement = document.createElement('div');
                    letraElement.className = "containerLetra";
                    const divLetra = document.createElement('div');
                    divLetra.className = "letra";
                    divLetra.innerHTML = `<p>${letra}</p>`+`<img src="../img/logo.svg" alt="logo">`;
                    letraElement.appendChild(divLetra);
                    resposta.appendChild(letraElement);
                    
                    resultadosPorLetra[letra].forEach(result => {
                        const resultElement = document.createElement('div');
                        resultElement.className = "medicos";

                        const medicoItem = document.createElement('div');
                        medicoItem.className = "medicoItem";

                        const pNome = document.createElement("p");
                        pNome.className = "medicoNome";
                        pNome.innerHTML = result.nome;
                        medicoItem.appendChild(pNome);

                        const especCrm = document.createElement("p");
                        especCrm.className = "medicoCampos";
                        especCrm.innerHTML = result.especialidade + " | CRM "+ result.crm;
                        medicoItem.appendChild(especCrm);

                        const email = document.createElement("p");
                        email.className = "medicoCampos";
                        email.innerHTML = result.email;
                        medicoItem.appendChild(email);

                        const telefone = document.createElement("p");
                        telefone.className = "medicoCampos";
                        telefone.innerHTML = result.telefone;
                        medicoItem.appendChild(telefone);

                        const endereco = document.createElement("p");
                        endereco.className = "medicoCampos";
                        endereco.innerHTML = result.endereco.logradouro + ", " + result.endereco.numero + " - " + result.endereco.cidade + "/" + result.endereco.uf;
                        medicoItem.appendChild(endereco);

                        const cep = document.createElement("p");
                        cep.className = "medicoCampos";
                        cep.innerHTML = "CEP: "+ result.endereco.cep;
                        medicoItem.appendChild(cep);

                        resultElement.appendChild(medicoItem);
                        // for (var key in result ) {
                            // const p = document.createElement("p");
                            // p.className = "json-key";
                            // p.innerHTML = `<span class="json-key">${key}:</span> <span class="json-value">${result[key]}</span>`;
                            // resultElement.appendChild(p);
                            
                        // }
                        letraElement.appendChild(resultElement);
                        console.log(result);
                    });
                }
            }
        });
}