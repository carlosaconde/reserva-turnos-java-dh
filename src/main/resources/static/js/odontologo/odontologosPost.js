window.addEventListener('load', function () {

    const formularioOdontologo = document.querySelector('#add_new_odontologo');
    console.log("dentro de odontologo post");
    formularioOdontologo.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value

        };


        const url = '/api/odontologos/';

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
                    '<strong></strong> Odontologo Agregado Correctamente </div>'

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
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";

    }

    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/odontologoPost.html") {
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/odontologoslist.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});