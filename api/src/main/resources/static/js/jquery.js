// Função para fazer a pesquisa com base no tipo selecionado
function doSearch() {
    const searchTerm = document.getElementById('search').value;
    const searchType = document.getElementById('searchType').value;

    // Verificar se o campo de pesquisa está vazio e o tipo de pesquisa selecionado não é vazio
    if (searchTerm === '' && searchType !== '') {
        // Se estiver vazio, faça uma chamada à API para obter todos os resultados
        // Substitua a URL abaixo pela URL real da sua API
        const apiUrl = `http://localhost:8080/${searchType}`;

        // Fazer a chamada à API usando fetch
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                // Verificar se 'data.content' é um array
                if (Array.isArray(data.content)) {
                    // Limpar os resultados anteriores
                    document.getElementById('results').innerHTML = '';

                    // Exibir os resultados na página
                    data.content.forEach(result => {
                        const resultElement = document.createElement('div');
                        resultElement.textContent = JSON.stringify(result);
                        document.getElementById('results').appendChild(resultElement);
                    });
                } else {
                    // Exibir os resultados na página
                    const resultElement = document.createElement('div');
                    resultElement.textContent = JSON.stringify(data);
                    document.getElementById('results').appendChild(resultElement);
                    console.error("Os dados da API não contêm um array 'content'.");
                }
            })
            .catch(error => console.error(error));
    } else {
        // Caso contrário, faça a pesquisa normalmente
        // Construir a URL da API com base no tipo de pesquisa e no termo inserido
        // Substitua a URL abaixo pela URL real da sua API
        const apiUrl = `http://localhost:8080/${searchType}/${searchTerm}`;

        // Fazer a chamada à API usando fetch
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                // Limpar os resultados anteriores
                document.getElementById('results').innerHTML = '';

                // Exibir os resultados na página
                data.forEach(result => {
                    const resultElement = document.createElement('div');
                    resultElement.textContent = JSON.stringify(result);
                    document.getElementById('results').appendChild(resultElement);
                });
            })
            .catch(error => console.error(error));
    }
}

// Adicionar um ouvinte de eventos para o botão de pesquisa
document.getElementById('search').addEventListener('input', doSearch);
document.getElementById('searchType').addEventListener('change', doSearch);