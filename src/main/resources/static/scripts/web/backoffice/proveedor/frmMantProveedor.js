$(document).on("click", "#btnagregar", function(){
    $("#hdidproveedor").val("0");
    $("#cbotipodoc").val("R");
    $("#txtdocumento").val("");
    $("#txtnombre").val("");
    $("#txtdireccion").val("");
    $("#txtdistrito").val("");
    $("#txtciudad").val("");
    $("#cbotipo").val("C");
    $("#txtcomentarios").val("");
    $("#swestado").hide();
    $("#cboestado").val("A");
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#hdidproveedor").val($(this).attr("data-id"));
    $("#cbotipodoc").val($(this).attr("data-tipo_documento"));
    $("#txtdocumento").val($(this).attr("data-documento"));
    $("#txtnombre").val($(this).attr("data-nombre"));
    $("#txtdireccion").val($(this).attr("data-direccion"));
    $("#txtdistrito").val($(this).attr("data-distrito"));
    $("#txtciudad").val($(this).attr("data-ciudad"));
    $("#cbotipo").val($(this).attr("data-tipo"));
    $("#txtcomentarios").val($(this).attr("data-comentarios"));
    $("#swestado").show();
    $("#cboestado").val($(this).attr("data-estado"));
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/backoffice/proveedor/guardar",
        contentType: "application/json",
        data: JSON.stringify({
            id: $("#hdidproveedor").val(),
            tipo_documento: $("#cbotipodoc").val(),
            documento: $("#txtdocumento").val(),
            nombre: $("#txtnombre").val(),
            direccion: $("#txtdireccion").val(),
            distrito: $("#txtdistrito").val(),
            ciudad: $("#txtciudad").val(),
            tipo: $("#cbotipo").val(),
            comentarios: $("#txtcomentarios").val(),
            estado: $("#cboestado").val(),
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarProveedores()
                /*$("#tblusuario > tbody").html("");*/
            }
            alert(resultado.mensaje);
            $("#modalNuevo").modal("hide");
        }
    })
});

function listarProveedores(){
    $.ajax({
        type: "GET",
        url: "/backoffice/proveedor/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblproveedor > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblproveedor > tbody").append("<tr>"+
                    "<td>"+value.id+"</td>"+
                    "<td>"+value.tipo_documento+"</td>"+
                    "<td>"+value.documento+"</td>"+
                    "<td>"+value.nombre+"</td>"+
                    "<td>"+value.direccion+"</td>"+
                    "<td>"+value.distrito+"</td>"+
                    "<td>"+value.ciudad+"</td>"+
                    "<td>"+value.tipo+"</td>"+
                    "<td>"+value.comentarios+"</td>"+
                    "<td>"+value.estado+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-info btnactualizar'" +
                            " data-id='"+value.id+"'"+
                            " data-tipo_documento='"+value.tipo_documento+"'"+
                            " data-documento='"+value.documento+"'"+
                            " data-nombre='"+value.nombre+"'"+
                            " data-direccion='"+value.direccion+"'"+
                            " data-distrito='"+value.distrito+"'"+
                            " data-ciudad='"+value.ciudad+"'"+
                            " data-tipo='"+value.tipo+"'"+
                            " data-comentarios='"+value.comentarios+"'"+
                            " data-estado='"+value.estado+"'"+
                            "><i class='fas fa-edit'></i></button></td></tr>"
                );
            })
        }
    })
};
