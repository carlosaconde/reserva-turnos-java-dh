window.addEventListener('load', function () {


    const formularioPaciente = document.querySelector('#add_new_paciente');


    formularioPaciente.addEventListener('submit', function (event) {
         event.preventDefault();

        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            domicilio:{
                        calle: document.querySelector('#calle').value,
                        numero: document.querySelector('#numero').value,
                        localidad: document.querySelector('#localidad').value,
                        provincia: document.querySelector('#provincia').value,
            }


        };

        const url = '/api/pacientes/';
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
                     '<strong></strong> Paciente Agregado Correctamente </div>'

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

                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
         document.querySelector('#dni').value = "";
         document.querySelector('#calle').value = "";
         document.querySelector('#numero').value = "";
         document.querySelector('#localidad').value = "";
         document.querySelector('#provincia').value = "";

    }
console.log("hola estoy dentro de paceintes");
    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/pacientesPost.html"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/pacientesList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});