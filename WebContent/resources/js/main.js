$(document).ready(function()
{
	$('#addDialog').dialog({
		autoOpen: false
	});
	
	$('#checkDialog').dialog({
		autoOpen: false
	});
	
	$('#addDialogButton').on('click', function(){
		$('#addDialog').dialog("open");
	});
	
	$('#checkDialogButton').on('click', function(){
		$('#checkDialog').dialog("open");
	});
	
	$('#addStudent').on('click', function(){
		addStudent();
	});
	
	$('#checkCode').on('click', function(){
		checkCode();
	});
});

function addStudent()
{
	var name = $('#name').val();
	var userName = $('#userName').val();
	
	$.ajax({
		url: "DataController?isAjax=true&isAddStudent=true&isCheckCode=false",
		data: {name: name, userName: userName},
		type: "post",
		success: function(response){
			alert(response);
		},
		error: function(){
			alert("Unable to add student!");
		}
	});
}

function checkCode()
{
	var userName = $('#checkUserName').val();
	var code = $('#code').val();
	
	$.ajax({
		url:  "DataController?isAjax=true&isAddStudent=false&isCheckCode=true",
		data: {userName: userName, code: code},
		type: "post",
		success: function(response)
		{
			alert("Code Validation: " + response);
		},
		error: function(){
			alert("Invalid");
		}
		
	})
}