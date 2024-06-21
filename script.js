const formularioPaciente = document.querySelector("#formulario-paciente");
const InomePaciente = formularioPaciente.querySelector(".nome");
const IcpfPaciente = formularioPaciente.querySelector(".cpf");
const IenderecoPaciente = formularioPaciente.querySelector(".endereco");
const IidadePaciente = formularioPaciente.querySelector(".idade");
const ItelefonePaciente = formularioPaciente.querySelector(".telefone");
const IconvenioPaciente = formularioPaciente.querySelector(".convenio");

function cadastrarPaciente() {
    fetch("http://localhost:8080/pacientes", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            nome: InomePaciente.value,
            cpf: IcpfPaciente.value,
            endereco: IenderecoPaciente.value,
            idade: parseInt(IidadePaciente.value),
            telefone: ItelefonePaciente.value,
            convenio: IconvenioPaciente.value
        })
    })
        .then(function (res) {
            if (!res.ok) {
                throw new Error("Network response was not ok " + res.statusText);
            }
            return res.json();
        })
        .then(function (data) {
            console.log("Success:", data);
            alert("Paciente cadastrado com sucesso!");
        })
        .catch(function (error) {
            console.error("Error:", error);
            alert("Erro ao cadastrar paciente: " + error.message);
        });
}

const formularioMedico = document.querySelector("#formulario-medico");
const InomeMedico = formularioMedico.querySelector(".nome");
const IcpfMedico = formularioMedico.querySelector(".cpf");
const IenderecoMedico = formularioMedico.querySelector(".endereco");
const IidadeMedico = formularioMedico.querySelector(".idade");
const ItelefoneMedico = formularioMedico.querySelector(".telefone");
const IcrmMedico = formularioMedico.querySelector(".crm");
const IespecialidadeMedico = formularioMedico.querySelector(".especialidade");

function cadastrarMedico() {
    fetch("http://localhost:8080/medicos", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            nome: InomeMedico.value,
            cpf: IcpfMedico.value,
            endereco: IenderecoMedico.value,
            idade: parseInt(IidadeMedico.value),
            telefone: ItelefoneMedico.value,
            crm: IcrmMedico.value,
            especialidade: IespecialidadeMedico.value
        })
    })
        .then(function (res) {
            if (!res.ok) {
                throw new Error("Network response was not ok " + res.statusText);
            }
            return res.json();
        })
        .then(function (data) {
            console.log("Success:", data);
            alert("Médico cadastrado com sucesso!");
        })
        .catch(function (error) {
            console.error("Error:", error);
            alert("Erro ao cadastrar médico: " + error.message);
        });
}

formularioPaciente.addEventListener('submit', function (event) {
    event.preventDefault();
    cadastrarPaciente();
    formularioPaciente.reset();
});

formularioMedico.addEventListener('submit', function (event) {
    event.preventDefault();
    cadastrarMedico();
    formularioMedico.reset();
});
