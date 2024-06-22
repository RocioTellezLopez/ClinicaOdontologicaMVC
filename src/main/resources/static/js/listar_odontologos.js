document.addEventListener("DOMContentLoaded", fetchOdontologos);

const tableBody = document.querySelector("#odontologosTable tbody");
const formularioEditar = document.getElementById("editarFormulario");
const modalEditarOdontologo = new bootstrap.Modal(document.getElementById('editarOdontologoModal'));
const modalEliminarOdontologo = new bootstrap.Modal(document.getElementById('eliminarOdontologoModal'));

let odontologoSeleccionado = null;

function fetchOdontologos() {
  // listando los odontologos

  fetch(`/odontologo`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((odontologo, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
                <td>${odontologo.id}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td>${odontologo.numeroMatricula}</td>
                <td>
                  <button class="btn btn-primary btn-sm" onclick="editOdontologo(${odontologo.id}, '${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.numeroMatricula}')">Modificar</button>
                  <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
                </td>
              `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
}

function editOdontologo(id, nombre, apellido, matricula) {
  // Mostrar modal y completar formulario con datos del odontólogo seleccionado
  odontologoSeleccionado = { id, nombre, apellido, matricula };

  document.getElementById("editNombre").value = nombre;
  document.getElementById("editApellido").value = apellido;
  document.getElementById("editMatricula").value = matricula;

  modalEditarOdontologo.show();

}

function guardarCambios() {
  // Obtener datos editados del formulario
  const nombre = document.getElementById("editNombre").value;
  const apellido = document.getElementById("editApellido").value;
  const matricula = document.getElementById("editMatricula").value;

  const odontologoActualizado = {
      id: odontologoSeleccionado.id,  // Incluimos el ID del odontólogo
      nombre,
      apellido,
      numeroMatricula: matricula
    };

  fetch(`/odontologo`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(odontologoActualizado),
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Error al actualizar el odontólogo');
    }
    return response.json();
  })
  .then(() => {
    // Actualizar la tabla después de guardar los cambios
    fetchOdontologos();
    // Cerrar el modal
    modalEditarOdontologo.hide();
  })
  .catch((error) => {
    console.error('Error actualizando odontólogo:', error);
  });
}


function deleteOdontologo(id) {
  odontologoSeleccionado = { id };
  modalEliminarOdontologo.show();
}

function confirmarEliminarOdontologo() {
  if (!odontologoSeleccionado) {
    console.error('No se ha seleccionado ningún odontólogo para eliminar.');
    return;
  }

  fetch(`/odontologo/${odontologoSeleccionado.id}`, {
    method: 'DELETE',
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Error al eliminar el odontólogo');
    }
    return response.json();
  })
  .then(() => {
    fetchOdontologos();
    modalEliminarOdontologo.hide();
  })
  .catch((error) => {
    console.error('Error eliminando odontólogo:', error);
  });
}



fetchOdontologos();