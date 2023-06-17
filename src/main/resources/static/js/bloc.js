 var initialTableHtml = null;

function loadBloc(blocListTableID){
        $.ajax({
              url: 'http://localhost:8080/eRegistrar/Block/edit/'+blocListTableID,
              type: 'GET',
              dataType: 'json',
              success: function(data) {
                let student = data;
                   console.log(data);
                   $('#updateBlocId').val(data.blockID);
                   $('#updateBlocName').val(data.blockName);
                   $('#updateStartingDate').val(data.startingDate);
                   $('#updateEndingDate').val(data.endingDate);
                   $("#updateBlocModal").show();
              },
              error: function(xhr, status, error) {
                console.log(error);
              }
        });
}

$('#searchString').on('keyup', function() {
    var inputValue = $(this).val();
    $('#blocListTable').empty();

    if(inputValue.length > 0){
        $.ajax({
              url: 'http://localhost:8080/eRegistrar/Block/search/'+inputValue,
              type: 'GET',
              dataType: 'json',
              success: function(data) {
                populateTable(data);
              },
              error: function(xhr, status, error) {
                console.log(error);
              }
        });
    }else{
        $('#blocListTable').append(initialTableHtml);
    }

});

function populateTable(data){
        $.each(data, function(index, item) {
                    var position = index+1;
                    var row = '<tr>' +
                                '<td><b>' + position + '.</b></td>' +
                                '<td>' + item.blockName + '</td>' +
                                '<td>' + item.startingDate + '</td>' +
                                '<td>' + item.endingDate + '</td>' +
                                '<td><a href="#" onclick="loadBloc('+item.blockID+')">Edit</a> | <a href="/eRegistrar/Block/delete/'+item.blockID+'">Delete</a></td>'

                              '</tr>';
                    $('#blocListTable').append(row);
        });
}

$(document).ready(function (e) {
    initialTableHtml = $('#blocListTable').html();

    $("#addNewBlocBtn").click(()=>{
        $("#newBlocModal").show();
    })

    $('.close').click(function() {
        $('.modal').hide();
    });

});
