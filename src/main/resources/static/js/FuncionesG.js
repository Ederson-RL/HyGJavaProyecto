function eliminar(Id){
    swal({
        title: "Esta seguro de Eliminar?",
        text: "Once deleted, you will not be able to recover this imaginary file!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((OK) => {
        if (OK) {

            $.ajax({
            url:"/eliminarG/" + Id,
            success: function (res) {
                console.log(res);
    
            }
        });
          swal("Poof! Your imaginary file has been deleted!", {
            icon: "success",
          }).then((OK)=>{
            if(OK){
                location.href="/listarG"
            }
          });
        } else {
          swal("Your imaginary file is safe!");
        }
      });
}