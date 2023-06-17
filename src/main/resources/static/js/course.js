 var initialTableHtml = null;

function loadCourse(courseID){
        $.ajax({
              url: 'http://localhost:8080/eRegistrar/Course/edit/'+courseID,
              type: 'GET',
              dataType: 'json',
              success: function(data) {
                let student = data;
                   console.log(data);
                   $('#updateCourseId').val(data.courseID);
                   $('#updateCourseName').val(data.courseName);
                   $("#updateCourseModal").show();
              },
              error: function(xhr, status, error) {
                console.log(error);
              }
        });
}

$('#searchString').on('keyup', function() {
    var inputValue = $(this).val();
    $('#courseListTable').empty();

    if(inputValue.length > 0){
        $.ajax({
              url: 'http://localhost:8080/eRegistrar/Course/search/'+inputValue,
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
        $('#courseListTable').append(initialTableHtml);
    }

});

function populateTable(data){
        $.each(data, function(index, item) {
                    var position = index+1;
                    var row = '<tr>' +
                                '<td><b>' + position + '.</b></td>' +
                                '<td>' + item.courseName + '</td>' +
                                '<td><a href="#" onclick="loadCourse('+item.courseID+')">Edit</a> | <a href="/eRegistrar/Student/delete/'+item.courseID+'">Delete</a></td>'

                              '</tr>';
                    $('#courseListTable').append(row);
        });
}

$(document).ready(function (e) {
    initialTableHtml = $('#courseListTable').html();

    $("#addNewCourseBtn").click(()=>{
        $("#newCourseModal").show();
    })

    $('.close').click(function() {
        $('.modal').hide();
    });

});
