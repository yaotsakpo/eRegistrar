 var initialTableHtml = null;

function loadStudent(studentID){
        $.ajax({
              url: 'http://localhost:8080/eRegistrar/Student/edit/'+studentID,
              type: 'GET',
              dataType: 'json',
              success: function(data) {
                let student = data;
                   console.log(data);
                   $('#updateStudentId').val(data.studentID);
                   $('#updateStudentNumber').val(data.studentNumber);
                   $('#updatefirstName').val(data.firstName);
                   $('#updatemiddleName').val(data.middleName)
                   $('#updatelastName').val(data.lastName)
                   $('#updateCgpa').val(data.cgpa)
                   $('#updateEnrollmentDate').val(data.enrollmentDate)
                   $('#updateIsInternational').val(data.international)
                    $('#UpdateInternationalCheckbox').prop('checked', data.international);
                    $("#updateStudentModal").show();

              },
              error: function(xhr, status, error) {
                console.log(error);
              }
        });
}

$('#searchString').on('keyup', function() {
    var inputValue = $(this).val();
    $('#studentListTable').empty();

    if(inputValue.length > 0){
        $.ajax({
              url: 'http://localhost:8080/eRegistrar/Student/search/'+inputValue,
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
        $('#studentListTable').append(initialTableHtml);
    }

});

function populateTable(data){
        $.each(data, function(index, item) {
                    var position = index+1;
                    var isInternational = item.international ? "Yes" : "No";
                    var row = '<tr>' +
                                '<td><b>' + position + '.</b></td>' +
                                '<td>' + item.studentNumber + '</td>' +
                                '<td>' + item.firstName + '</td>' +
                                '<td>' + item.middleName + '</td>' +
                                '<td>' + item.lastName + '</td>' +
                                '<td>' + item.cgpa + '</td>' +
                                '<td>' + item.enrollmentDate + '</td>' +
                                '<td>' + isInternational  + '</td>' +
                                '<td><a href="#" onclick="loadStudent('+item.studentID+')">Edit</a> | <a href="/eRegistrar/Student/delete/'+item.studentID+'">Delete</a></td>'

                              '</tr>';
                    $('#studentListTable').append(row);
        });
}

$(document).ready(function (e) {
    initialTableHtml = $('#studentListTable').html();

    $("#addNewStudenBtn").click(()=>{
        $("#newStudentModal").show();
    })

    $('#internationalCheckbox').change(function(){
        $('#isInternational').val($('#internationalCheckbox').is(':checked'))
    })

    $('#UpdateInternationalCheckbox').change(function(){
        $('#updateIsInternational').val($('#UpdateInternationalCheckbox').is(':checked'))
    })

    $('.close').click(function() {
        $('.modal').hide();
    });

});
