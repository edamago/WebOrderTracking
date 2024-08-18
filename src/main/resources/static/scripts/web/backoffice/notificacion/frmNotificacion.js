$(document).on("click", "#btnagregar", function(){

    listarUsuarios("")

    // Ocultar los campos cbocliente y cboProducto
    //$("label[for='cboProducto']").hide();
    //$("#cboProducto").hide();


    // Cargar los datos en el modal
    $("#hddidnotificacion").val("0");
    $("#txtdescripcion").val("");
    //$("#chkEnviado").prop("checked", false);
    $("#chkvisto").prop("checked", false);
    $("#cboestado").val("A");
    $("#cbousuario").val("0");

    // Obtener el usuario actual
    //const currentUsuarioId = $(this).attr("data-notificacionidusuario");



    // Habilitar los campos
    $("#cbousuario").prop("disabled", false);


    // Mostrar el modal
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    // Visibilizar los campos cbocliente y cboProducto
    //$("label[for='cboProducto']").show();
    //$("#cboProducto").show();


    // Cargar los datos en el modal
    $("#hddidnotificacion").val($(this).attr("data-notificacionid"));
    $("#txtdescripcion").val($(this).attr("data-notificaciondescripcion"));
    //$("#chkEnviado").prop("checked", false);
    $("#chkvisto").prop("checked", $(this).attr("data-notificacionvisto") === "1");
    $("#cboestado").val($(this).attr("data-notificacionestado"));
    $("#cbousuario").val($(this).attr("data-notificacionidusuario"));

    // Obtener el usuario actual
    const currentUsuarioId = $(this).attr("data-notificacionidusuario");

    listarUsuarios(currentUsuarioId)

    // Deshabilitar los campos
    $("#cbousuario").prop("disabled", true);


    // Mostrar el modal
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    // Obtener el estado del checkbox y convertirlo en 0 o 1
    const vistoValue = $("#chkvisto").is(":checked") ? 1 : 0;
    $.ajax({
        type: "POST",
        url: "/backoffice/notificacion/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            id: $("#hddidnotificacion").val(),
            descripcion: $("#txtdescripcion").val(),
            estado: $("#cboestado").val(),
            visto: vistoValue,
            t_usuario_id: $("#cbousuario").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarDetalle()
                /*$("#tblusuario > tbody").html("");*/
            }
            alert(resultado.mensaje);
            $("#modalNuevo").modal("hide");
        }
    })
});

function listarEstados(currentEstadoId){
 // Llenar el combobox de estado
    $.ajax({
        url: '/backoffice/estado/listar',
        method: 'GET',
        success: function(estados) {
            const estadoSelect = $("#cboestado");
            estadoSelect.empty(); // Limpiar las opciones existentes

            estados.forEach(function(estado) {
                const selected = currentEstadoId == estado.id ? 'selected' : '';
                estadoSelect.append(`<option value="${estado.id}" ${selected}>${estado.descripcion}</option>`);
            });
        },
        error: function() {
            console.error('No se pudo cargar los estados.');
        }
    });
}

function listarClientes(currentClienteId){
 // Llenar el combobox de estado
    $.ajax({
        url: '/backoffice/cliente/listar',
        method: 'GET',
        success: function(clientes) {
            const clienteSelect = $("#cbocliente");
            clienteSelect.empty(); // Limpiar las opciones existentes

            clientes.forEach(function(cliente) {
                const selected = currentClienteId == cliente.id ? 'selected' : '';
                clienteSelect.append(`<option value="${cliente.id}" ${selected}>${cliente.nombre}</option>`);
            });
        },
        error: function() {
            console.error('No se pudo cargar los clientes.');
        }
    });
}

function listarProductos(currentProductoId){
 // Llenar el combobox de estado
    $.ajax({
        url: '/backoffice/producto/listar',
        method: 'GET',
        success: function(productos) {
            const productoSelect = $("#cboProducto");
            productoSelect.empty(); // Limpiar las opciones existentes

            productos.forEach(function(producto) {
                const selected = currentProductoId == producto.id ? 'selected' : '';
                productoSelect.append(`<option value="${producto.id}" ${selected}>${producto.descripcion}</option>`);
            });
        },
        error: function() {
            console.error('No se pudo cargar los productos.');
        }
    });
}
function listarUsuarios(currentUsuarioId){
 // Llenar el combobox de estado
    $.ajax({
        url: '/backoffice/usuario/listar',
        method: 'GET',
        success: function(usuarios) {
            const usuarioSelect = $("#cbousuario");
            usuarioSelect.empty(); // Limpiar las opciones existentes

            usuarios.forEach(function(usuario) {
                const selected = currentUsuarioId == usuario.id ? 'selected' : '';

                const descripcionCompleta = `${usuario.nombre} - ${usuario.ap_paterno} - ${usuario.ap_materno}`;

                usuarioSelect.append(`<option value="${usuario.id}" ${selected}>${descripcionCompleta}</option>`);
            });
        },
        error: function() {
            console.error('No se pudo cargar los usuarios.');
        }
    });
}
function listarPosiciones(currentPosicionId){
 // Llenar el combobox de estado
    $.ajax({
        url: '/backoffice/posiciones/listar',
        method: 'GET',
        success: function(posiciones) {
            const posicionesSelect = $("#txtidPosicion");
            posicionesSelect.empty(); // Limpiar las opciones existentes

            posiciones.forEach(function(posicion) {
                const selected = currentPosicionId == posicion.id ? 'selected' : '';
                //posicionesSelect.append(`<option value="${posicion.id}" ${selected}>${posicion.producto.descripcion}</option>`);
                const descripcionCompleta = `ID: ${posicion.id} - ${posicion.pedido.cliente.nombre} - ${posicion.producto.descripcion}`;

                posicionesSelect.append(`<option value="${posicion.id}" ${selected}>${descripcionCompleta}</option>`);
            });
        },
        error: function() {
            console.error('No se pudo cargar los productos.');
        }
    });
}

function listarDetalle(){
    $.ajax({
        type: "GET",
        url: "/backoffice/notificacion/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblnotificacion > tbody").html("");
            $.each(resultado, function(index, value){

                const checkboxVisto = value.visto ? "<i class='fas fa-check-square'></i>" : "<i class='far fa-square'></i>";

                $("#tblnotificacion > tbody").append("<tr>"+
                    "<td>"+value.usuario.nombre+"</td>"+
                    "<td>"+value.id+"</td>"+
                    "<td>"+value.descripcion+"</td>"+
                    "<td>"+value.estado+"</td>"+
                    "<td>"+checkboxVisto+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-info btnactualizar'" +
                            " data-notificacionid='"+value.id+"'"+
                            " data-notificaciondescripcion='"+value.descripcion+"'"+
                            " data-notificacionestado='"+value.estado+"'"+
                            " data-notificacionvisto='"+value.visto+"'"+
                            " data-notificacionidusuario='"+value.usuario.id+"'"+
                            "><i class='fas fa-edit'></i></button></td></tr>"
                );
            })
        }
    })
};

document.addEventListener('DOMContentLoaded', function () {
    const filterUsuarioInput = document.getElementById('filterUsuario');

    const table = document.getElementById('tblnotificacion');
    const rows = table.getElementsByTagName('tr');

    function filterTable() {
        const filterUsuarioValue = filterUsuarioInput.value.toLowerCase();


        for (let i = 1; i < rows.length; i++) { // Comienza en 1 para saltar el encabezado
            const cells = rows[i].getElementsByTagName('td');
            const idUsuarioCell = cells[0]; // Columna de usuario


            const iUsuarioText = idUsuarioCell ? idUsuarioCell.textContent.toLowerCase() : '';


            if (
                iUsuarioText.includes(filterUsuarioValue)
            ) {
                rows[i].style.display = ''; // Mostrar fila
            } else {
                rows[i].style.display = 'none'; // Ocultar fila
            }
        }
    }

    filterUsuarioInput.addEventListener('keyup', filterTable);

});
