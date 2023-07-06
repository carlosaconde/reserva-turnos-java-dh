window.addEventListener('load', function () {

    const selectPaciente = document.getElementById("pacientesOptions");
    const selectOdontologo = document.getElementById("odontologosOptions");

    const urlPacientes = '/api/pacientes/';
    const settingsPacientes = {
        method: 'GET'
    }
    fetch(urlPacientes, settingsPacientes)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            for (paciente of data) {

                console.log(paciente);
                var option = document.createElement("option");
                option.value = paciente.id;
                option.text = paciente.apellido + ", " + paciente.nombre;
                selectPaciente.appendChild(option);

            };
        });


    const urlOdontologos = '/api/odontologos/';
    const settings = {
        method: 'GET'
    }
    fetch(urlOdontologos, settings)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            for (odontologo of data) {

                console.log(odontologo);
                var option = document.createElement("option");
                option.value = odontologo.id;
                option.text = odontologo.apellido + ", " + odontologo.nombre;
                selectOdontologo.appendChild(option);
                console.log(option.value);
            };
        });










    const formularioTurno = document.querySelector('#add_new_turno');

    formularioTurno.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = {
            odontologo: {
                id: +document.querySelector('#odontologosOptions').value,
            },
            paciente: {
                id: +document.querySelector('#pacientesOptions').value,
            },
            fechaTurno: document.querySelector('#fechaTurno').value,
            hora: document.querySelector('#hora').value

        };

        const url = '/api/turnos/';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Turno Creado Correctamente </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();

            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            })
    });


    function resetUploadForm() {
        document.getElementById('pacientesOptions').value = "0";
        document.getElementById('odontologosOptions').value = "0";
        document.querySelector('#fechaTurno').value = "";
        document.querySelector('#hora').value = "";

    }

    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/turnosPost.html") {
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnosList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();

});