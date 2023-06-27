window.addEventListener('load', function () {


var campoPaciente = document.getElementById("paciente");
var campoOdontologo = document.getElementById("odontologo");
var selectPaciente = document.getElementById("pacientesOptions");
var selectOdontologo = document.getElementById("odontologosOptions");

                      const urlPacientes = '/api/pacientes/';
                      const settingsPacientes = {
                        method: 'GET'
                      }
                      fetch(urlPacientes,settingsPacientes)
                      .then(response => response.json())
                      .then(data => {
                        console.log(data);
                         for(paciente of data){

                         console.log(paciente);
                            var option = document.createElement("option");
                            option.value=paciente.id;
                            option.text= paciente.apellido+ ", " +paciente.nombre;
                            selectPaciente.appendChild(option);

                         };
                         });


                      const urlOdontologos = '/api/odontologos/';
                      const settings = {
                        method: 'GET'
                      }
                      fetch(urlOdontologos,settings)
                      .then(response => response.json())
                      .then(data => {
                        console.log(data);
                         for(odontologo of data){

                         console.log(odontologo);
                            var option = document.createElement("option");
                            option.value=odontologo.id;
                            option.text= odontologo.apellido+ ", " +odontologo.nombre ;
                            selectOdontologo.appendChild(option);
                            console.log(option.value);
                         };
                         });

        selectPaciente.addEventListener("change",function(){
            var pacienteSeleccionado=selectPaciente.value;
            campoPaciente.value=pacienteSeleccionado;

        });

       selectOdontologo.addEventListener("change",function(){
            var odontologoSeleccionado=selectOdontologo.value;
            campoOdontologo.value=odontologoSeleccionado;

        });






    //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo turno
    const formularioTurno = document.querySelector('#add_new_turno');

    //Ante un submit del formulario se ejecutará la siguiente funcion
    formularioTurno.addEventListener('submit', function (event) {
       event.preventDefault();
       //creamos un JSON que tendrá los datos del nuevo turno
        const formData = {
            odontologo: {
                id: document.querySelector('#odontologo').value,
            },
            paciente: {
                 id: document.querySelector('#paciente').value,
                 },
            fechaTurno: document.querySelector('#fechaTurno').value,
            hora: document.querySelector('#hora').value

        };

        //invocamos utilizando la función fetch la API turnos con el método POST que guardará
        //el turno que enviaremos en formato JSON
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
                 //Si no hay ningun error se muestra un mensaje diciendo que el turno
                 //se agrego bien
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Turno Creado Correctamente </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                    //Si hay algun error se muestra un mensaje diciendo que el turno
                    //no se pudo guardar y se intente nuevamente
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     //se dejan todos los campos vacíos por si se quiere ingresar otro turno
                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#odontologo').value = "";
        document.querySelector('#paciente').value = "";
        document.querySelector('#fechaTurno').value = "";
        document.querySelector('#hora').value= "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/turnosPost.html"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnosList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();

});