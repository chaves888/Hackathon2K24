function deleteAgendamento(id) {
    if (confirm('Tem certeza que deseja excluir este agendamento?')) {
        fetch(`http://localhost:3001/agendamentos/${id}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
            })
            .then(response => {
                if (response.ok) {
                    alert('Agendamento excluído com sucesso.');
                    location.reload();
                } else {
                    alert('Erro ao excluir o agendamento.');
                }
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Erro ao excluir o agendamento.');
            });
    }
}

function abrirModalEditar(id) {

    fetch(`http://localhost:3001/agendamentos/${id}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('nome_idoso').value = data.agendamento.nome_idoso;
            document.getElementById('nome_vacina').value = data.agendamento.nome_vacina;
            document.getElementById('agendamento').value = data.agendamento.agendamento;
            $('#modalEditar').modal('show');
        })
        .catch(error => {
            console.error('Erro ao carregar os dados do agendamento.', error);
            alert('Erro ao carregar os dados do agendamento.');
        });


    document.getElementById('formEditarAgendamento').onsubmit = function(event) {
        event.preventDefault();
        const formData = new FormData(this);
        fetch(`http://localhost:3001/agendamento/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(Object.fromEntries(formData))
            })
            .then(response => {
                if (response.ok) {
                    alert('Agendamento atualizado com sucesso.');
                    location.reload();
                } else {
                    alert('Erro ao atualizar o agendamento.');
                }
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Erro ao atualizar o agendamento.');
            });
    };
}