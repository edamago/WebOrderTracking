$(document).on("click", "#btnagregar", function(){
    // Ocultar los campos cbocliente y cboProducto
    $("label[for='cboProducto']").hide();
    $("#cboProducto").hide();
    $("label[for='cbocliente']").hide();
    $("#cbocliente").hide();

    // Habilitar los campos
    $("#cboestado").prop("disabled", false);
    $("#cboProducto").prop("disabled", false);
    $("#cbocliente").prop("disabled", false);
    $("#txtidPosicion").prop("disabled", false);

    // Cargar los datos en el modal
    $("#hdidseguimiento").val("0");
    $("#txtidPosicion").val("2");


    $("#txtcomentario").val("");
    // Establecer chkEnviado como desmarcado por defecto
    $("#chkEnviado").prop("checked", false);

    // Obtener la fecha actual en formato yyyy-mm-dd
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0'); // Meses comienzan en 0
    const day = String(today.getDate()).padStart(2, '0');
    const formattedDate = `${year}-${month}-${day}`;

    // Establecer la fecha actual en el campo txtfecha
    $("#txtfecha").val(formattedDate);

    listarEstados("")
    listarClientes("")
    listarProductos("")
    listarPosiciones("")

    // Mostrar el modal
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    // Visibilizar los campos cbocliente y cboProducto
    $("label[for='cboProducto']").show();
    $("#cboProducto").show();
    $("label[for='cbocliente']").show();
    $("#cbocliente").show();

    // Cargar los datos en el modal
    $("#hdidseguimiento").val($(this).attr("data-id"));
    $("#txtidPosicion").val($(this).attr("data-iddetalle"));
    //$("#txtproducto").val($(this).attr("data-nombreproducto"));
    //$("#txtcliente").val($(this).attr("data-nombrecliente"));
    $("#txtfecha").val($(this).attr("data-fecha"));
    $("#txtcomentario").val($(this).attr("data-comentario"));

    //$("#chkEnviado").prop("checked", false);
    $("#chkEnviado").prop("checked", $(this).attr("data-enviado") === "1");

    //$("#cboestado").val($(this).attr("data-idestado"));
    //$("#cbocliente").val($(this).attr("data-idcliente"));

    // Obtener el estado actual
    const currentPosicionId = $(this).attr("data-iddetalle");
    const currentEstadoId = $(this).attr("data-idestado");
    const currentClienteId = $(this).attr("data-idcliente");
    const currentProductoId = $(this).attr("data-idproducto");

    listarEstados(currentEstadoId)
    listarClientes(currentClienteId)
    listarProductos(currentProductoId)
    listarPosiciones(currentPosicionId)

    // Deshabilitar los campos
    //$("#cboestado").prop("disabled", true);
    $("#cboProducto").prop("disabled", true);
    $("#cbocliente").prop("disabled", true);
    $("#txtidPosicion").prop("disabled", true);


    // Mostrar el modal
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    // Obtener el estado del checkbox y convertirlo en 0 o 1
    const enviadoValue = $("#chkEnviado").is(":checked") ? 1 : 0;
    $.ajax({
        type: "POST",
        url: "/backoffice/seguimiento/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            id: $("#hdidseguimiento").val(),
            fecha: $("#txtfecha").val(),
            comentario: $("#txtcomentario").val(),
            enviado: enviadoValue,
            t_estado_id: $("#cboestado").val(),
            t_detalle_pedido_id: $("#txtidPosicion").val(),
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
                const descripcionCompleta = `Pedido N°: ${posicion.pedido.id} - Posición N°: ${posicion.id} - ${posicion.pedido.cliente.nombre} - ${posicion.producto.descripcion}`;

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
        url: "/backoffice/seguimiento/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblseguimiento > tbody").html("");
            $.each(resultado, function(index, value){

                const checkboxEnviado = value.enviado ? "<i class='fas fa-check-square'></i>" : "<i class='far fa-square'></i>";

                $("#tblseguimiento > tbody").append("<tr>"+
                    "<td>"+value.id+"</td>"+
                    "<td>"+value.detallePedido.id+"</td>"+
                    "<td>"+value.detallePedido.producto.descripcion+"</td>"+
                    "<td>"+value.detallePedido.pedido.cliente.nombre+"</td>"+
                    "<td>"+value.fecha+"</td>"+
                    "<td>"+value.comentario+"</td>"+
                    //"<td><input type='checkbox' disabled "+checkboxEnviado+"></td>"+
                    "<td>"+checkboxEnviado+"</td>"+
                    "<td>"+value.estado.descripcion+"</td>"+
                    "<td>"+value.detallePedido.pedido.id+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-info btnactualizar'" +
                            " data-id='"+value.id+"'"+
                            " data-fecha='"+value.fecha+"'"+
                            " data-comentario='"+value.comentario+"'"+
                            " data-enviado='"+value.enviado+"'"+
                            " data-idestado='"+value.estado.id+"'"+
                            " data-nombreestado='"+value.estado.descripcion+"'"+
                            " data-iddetalle='"+value.detallePedido.id+"'"+
                            " data-idproducto='"+value.detallePedido.producto.id+"'"+
                            " data-nombreproducto='"+value.detallePedido.producto.descripcion+"'"+
                            " data-idcliente='"+value.detallePedido.pedido.cliente.id+"'"+
                            " data-nombrecliente='"+value.detallePedido.pedido.cliente.nombre+"'"+
                            "><i class='fas fa-edit'></i></button></td></tr>"
                );
            })
        }
    })
};

document.addEventListener('DOMContentLoaded', function () {
    const filterIdPedidoInput = document.getElementById('filterIdPedido');
    const filterIdInput = document.getElementById('filterIdDetalle');
    const filterClienteInput = document.getElementById('filterCliente');
    const filterProductoInput = document.getElementById('filterProducto');
    const table = document.getElementById('tblseguimiento');
    const rows = table.getElementsByTagName('tr');

    function filterTable() {
        const filterIdPedidoValue = filterIdPedidoInput.value.toLowerCase();
        const filterIdValue = filterIdInput.value.toLowerCase();
        const filterClienteValue = filterClienteInput.value.toLowerCase();
        const filterProductoValue = filterProductoInput.value.toLowerCase();

        for (let i = 1; i < rows.length; i++) { // Comienza en 1 para saltar el encabezado
            const cells = rows[i].getElementsByTagName('td');
            const idDetalleCell = cells[1]; // Columna de posición
            const productoCell = cells[2]; // Columna de producto
            const clienteCell = cells[3]; // Columna de cliente

            const idPedidoCell = cells[8]; // Columna de n° pedido

            const idDetalleText = idDetalleCell ? idDetalleCell.textContent.toLowerCase() : '';
            const productoText = productoCell ? productoCell.textContent.toLowerCase() : '';
            const clienteText = clienteCell ? clienteCell.textContent.toLowerCase() : '';

            const idPedidoText = idPedidoCell ? idPedidoCell.textContent.toLowerCase() : '';

            if (
                idDetalleText.includes(filterIdValue) &&
                productoText.includes(filterProductoValue) &&
                clienteText.includes(filterClienteValue) &&
                idPedidoText.includes(filterIdPedidoValue)
            ) {
                rows[i].style.display = ''; // Mostrar fila
            } else {
                rows[i].style.display = 'none'; // Ocultar fila
            }
        }
    }

    filterIdInput.addEventListener('keyup', filterTable);
    filterClienteInput.addEventListener('keyup', filterTable);
    filterProductoInput.addEventListener('keyup', filterTable);
    filterIdPedidoInput.addEventListener('keyup', filterTable);
});
