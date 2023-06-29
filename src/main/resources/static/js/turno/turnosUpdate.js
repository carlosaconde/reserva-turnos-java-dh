window.addEventListener('load', function () {




    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del turno
    const formularioTurno = document.querySelector('#update_turno_form');

    formularioTurno.addEventListener('submit', function (event) {
   event.PreventDefault();
        let turnoId = document.querySelector('#turno_id').value;

        //creamos un JSON que tendrá los datos del turno
        //a diferencia de un turno nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#turno_id').value,
            odontologo: {
                id:document.querySelector('#odontologo_id').value,
            },
            paciente: {
                id:document.querySelector('#paciente_id').value,
            },
            fechaTurno: document.querySelector('#fecha').value,
            hora: document.querySelector('#hora').value
        };

        //invocamos utilizando la función fetch la API turnos con el método PUT que modificará
        //el turno que enviaremos en formato JSON
        const url = '/api/turnos'+ "/" + turnoId;
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())


    })

    var campoPacienteUpdate = document.getElementById("paciente_id");
        var campoOdontologoUpdate = document.getElementById("odontologo_id");
        var selectPacienteUpdate = document.getElementById("pacientesOptionsUpdate");
        var selectOdontologoUpdate = document.getElementById("odontologosOptionsUpdate");

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
                                    selectPacienteUpdate.appendChild(option);

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
                                    selectOdontologoUpdate.appendChild(option);
                                    console.log(option.value);
                                 };
                                 });

                selectPacienteUpdate.addEventListener("change",function(){
                    var pacienteSeleccionadoUpdate=selectPacienteUpdate.value;
                    campoPacienteUpdate.value=pacienteSeleccionadoUpdate;

                });

               selectOdontologoUpdate.addEventListener("change",function(){
                    var odontologoSeleccionadoUpdate=selectOdontologoUpdate.value;
                    campoOdontologoUpdate.value=odontologoSeleccionadoUpdate;

                });



 })

    //Es la funcion que se invoca cuando se hace click sobre el id de un turno del listado
    //se encarga de llenar el formulario con los datos del turno
    //que se desea modificar
function findBy(id) {


        console.log(id)
              const url = '/api/turnos'+"/"+id;
              const settings = {
                  method: 'GET'
              }
              fetch(url,settings)
              .then(response => response.json())
              .then(data => {

                  let turno = data;
                  console.log(turno.paciente.id);
                  document.querySelector('#turno_id').value = turno.id;
                  document.querySelector('#odontologo_id').value = turno.odontologo.id;
                  document.querySelector('#paciente_id').value = turno.paciente.id;
                  document.querySelector('#fecha').value = turno.fechaTurno;
                  document.querySelector('#hora').value = turno.hora;

                  //el formulario por default esta oculto y al editar se habilita
                  document.querySelector('#div_turno_updating').style.display = "block";
              })
              .catch(error => {
                            alert("Error: " + error);
                        })
          }