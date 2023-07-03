window.addEventListener('load', function () {

    (function(){
let userName = document.getElementById("user");
    const urlUser = '/api/usuarios/usuarioLogueado';
          const settingsUser = {
            method: 'GET'
          }
          fetch(urlUser,settingsUser)
              .then(response => response.json())
                .then(data => {
                console.log(data)})


      const url = '/api/turnos/';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

         for(turno of data){

            var table = document.getElementById("turnoTable");
            var turnoRow =table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                      ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                                      turno.id +
                                      '</button>';

            turnoRow.innerHTML =
                    '<td>' + updateButton + '</td>' +
                    '<td class=\"td_odontologo\">' + turno.odontologo.apellido + ", "+ turno.odontologo.nombre + '</td>' +
                    '<td class=\"td_paciente\">' + turno.paciente.apellido + ", " + turno.paciente.nombre + '</td>' +
                    '<td class=\"td_fecha\">' + turno.fechaTurno + '</td>' +
                    '<td class=\"td_hora\">' + turno.hora + '</td>' +
                    '<td>' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/turnosList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })