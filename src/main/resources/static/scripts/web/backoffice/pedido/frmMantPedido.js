$(document).ready(function () {
    $('#btnAgregarProducto').on('click', function () {

        var producto = $('#cboproducto option:selected').text();
        var productoId = $('#cboproducto').val();  // Captura el ID del producto
                console.log('Producto seleccionado:', producto);
        var cantidad = $('#txtcantidad').val();
        var precio = $('#txtprecio').val();
        var comentarios = $('#txtcomentariosdet').val();

        if (!producto || !cantidad || !precio) {
            alert('Por favor, complete todos los campos.');
            return;
        }


        var newRow = $('<tr>');
        newRow.append('<td>' + productoId + '</td>');  // Puedes asignar un valor si es necesario
        newRow.append('<td>' + producto + '</td>');
        newRow.append('<td>' + cantidad + '</td>');
        newRow.append('<td>' + precio + '</td>');
        newRow.append('<td>' + comentarios + '</td>'); // Puedes asignar un valor si es necesario
        newRow.append('<td>A</td>'); // Puedes asignar un valor si es necesario



        $('#tbldetallepedido tbody').append(newRow);


        $('#cboproducto').val('');
        $('#txtcantidad').val('');
        $('#txtprecio').val('');
    });
});

$(document).on("click", "#btnagregar", function(){
    //$("#hdidcliente").val("0");
    //$("#cbotipodoc").val("R");
    //$("#txtdocumento").val("");
    //$("#txtnombre").val("");
    //$("#txtdireccion").val("");
    //$("#txtdireccione").val("");
    //$("#txtdistrito").val("");
    //$("#txtciudad").val("");
    //$("#cbotipo").val("C");
    //$("#cbocomercial").val("E");
    //$("#txtcomentarios").val("");
    //$("#swestado").hide();
    //$("#cboestado").val("A");
    listarClientes(0);
    listarProductos(0);
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function() {
    // Cargar los valores del pedido a partir de los atributos data-* del botón
    $("#hdidpedido").val($(this).attr("data-id"));
    $("#txtfecha").val($(this).attr("data-fecha"));
    $("#txtatencion").val($(this).attr("data-atencion"));
    $("#cbomoneda").val($(this).attr("data-moneda"));
    $("#txtcomentarios").val($(this).attr("data-comentario"));
    $("#cboestado").val($(this).attr("data-estado"));

    // Cargar cliente seleccionado en el select
    var clienteId = $(this).attr("data-t_cliente_id");
    var clienteNombre = $(this).attr("data-t_cliente_nombre");

    var cbocliente = $("#cbocliente");
    cbocliente.empty();  // Limpiar las opciones anteriores
    cbocliente.append($('<option>', {
        value: clienteId,
        text: clienteNombre,
        selected: true
    }));

    listarClientes(clienteId);  // Si es necesario, carga otros clientes para el select
    listarProductos(0);  // Si es necesario, carga los productos

    $("#modalNuevo").modal("show");
});

//$(document).on("click", ".btnactualizar", function(){
    //$("#hdidcliente").val($(this).attr("data-id"));
    //$("#cbotipodoc").val($(this).attr("data-tipo_documento"));
    //$("#txtdocumento").val($(this).attr("data-documento"));
    //$("#txtnombre").val($(this).attr("data-nombre"));
    //$("#txtdireccion").val($(this).attr("data-direccion"));
    //$("#txtdireccione").val($(this).attr("data-direccion_entrega"));
    //$("#txtdistrito").val($(this).attr("data-distrito"));
    //$("#txtciudad").val($(this).attr("data-ciudad"));
    //$("#cbotipo").val($(this).attr("data-tipo"));
    //$("#cbocomercial").val($(this).attr("data-clasif_comercial"));
    //$("#txtcomentarios").val($(this).attr("data-comentarios"));
    //$("#swestado").show();
    //$("#cboestado").val($(this).attr("data-estado"));
    //listarClientes(0);
    //listarProductos(0);
    //$("#modalNuevo").modal("show");
//});

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/backoffice/cliente/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            id: $("#hdidcliente").val(),
            tipo_documento: $("#cbotipodoc").val(),
            documento: $("#txtdocumento").val(),
            nombre: $("#txtnombre").val(),
            direccion: $("#txtdireccion").val(),
            direccion_entrega: $("#txtdireccione").val(),
            distrito: $("#txtdistrito").val(),
            ciudad: $("#txtciudad").val(),
            tipo: $("#cbotipo").val(),
            clasif_comercial: $("#cbocomercial").val(),
            comentarios: $("#txtcomentarios").val(),
            estado: $("#cboestado").val(),
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarClientes()
                /*$("#tblusuario > tbody").html("");*/
            }
            alert(resultado.mensaje);
            $("#modalNuevo").modal("hide");
        }
    })
});

function listarClientes(idcliente){
    $.ajax({
        type: "GET",
        url: "/backoffice/cliente/listar",
        dataType: "json",
        success: function(resultado){
            $.each(resultado, function(index, value){
                $("#cbocliente").append(
                `<option value="${value.id}">${value.nombre}</option>`
                )
            })
            if(idcliente > 0) {
                $("#cbocliente").val(idcliente);
            }

        }
    })
}
function listarProductos(idproducto){
    $.ajax({
        type: "GET",
        url: "/backoffice/producto/listar",
        dataType: "json",
        success: function(resultado){
            $.each(resultado, function(index, value){
                $("#cboproducto").append(
                `<option value="${value.id}">${value.descripcion}</option>`
                )
            })
            if(idproducto > 0) {
                $("#cboproducto").val(idproducto);
            }

        }
    })
}

function listarPedidos(){
    $.ajax({
        type: "GET",
        url: "/backoffice/cliente/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblcliente > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblcliente > tbody").append("<tr>"+
                    "<td>"+value.id+"</td>"+
                    "<td>"+value.tipo_documento+"</td>"+
                    "<td>"+value.documento+"</td>"+
                    "<td>"+value.nombre+"</td>"+
                    "<td>"+value.direccion+"</td>"+
                    "<td>"+value.direccion_entrega+"</td>"+
                    "<td>"+value.distrito+"</td>"+
                    "<td>"+value.ciudad+"</td>"+
                    "<td>"+value.tipo+"</td>"+
                    "<td>"+value.clasif_comercial+"</td>"+
                    "<td>"+value.comentarios+"</td>"+
                    "<td>"+value.estado+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-info btnactualizar'" +
                            " data-id='"+value.id+"'"+
                            " data-tipo_documento='"+value.tipo_documento+"'"+
                            " data-documento='"+value.documento+"'"+
                            " data-nombre='"+value.nombre+"'"+
                            " data-direccion='"+value.direccion+"'"+
                            " data-direccion_entrega='"+value.direccion_entrega+"'"+
                            " data-distrito='"+value.distrito+"'"+
                            " data-ciudad='"+value.ciudad+"'"+
                            " data-tipo='"+value.tipo+"'"+
                            " data-clasif_comercial='"+value.clasif_comercial+"'"+
                            " data-comentarios='"+value.comentarios+"'"+
                            " data-estado='"+value.estado+"'"+
                            "><i class='fas fa-edit'></i></button></td></tr>"
                );
            })
        }
    })
};