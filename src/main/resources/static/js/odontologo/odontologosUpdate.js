window.addEventListener('load', function () {

    const formularioOdontologo = document.querySelector('#update_odontologo_form');

    formularioOdontologo.addEventListener('submit', function (event) {
        let odontologoId = document.querySelector('#odontologo_id').value;


        const formData = {
            id: document.querySelector('#odontologo_id').value,
            apellido: document.querySelector('#apellido').value,
            nombre: document.querySelector('#nombre').value,
            matricula: document.querySelector('#matricula').value,

        };


        const url = '/api/odontologos/'+ odontologoId;
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
 })

    function findBy(id) {
          const url = '/api/odontologos'+ "/" + id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let odontologo = data;
              document.querySelector('#odontologo_id').value = odontologo.id;
              document.querySelector('#apellido').value = odontologo.apellido;
              document.querySelector('#nombre').value = odontologo.nombre;
              document.querySelector('#matricula').value = odontologo.matricula;

              document.querySelector('#div_odontologo_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }