$(document).ready(function() {
	$('#contractTable').DataTable();
	
	$('#contractTable tbody').on('click', '.contractRow', function() {
		location.href = '/contract/' + $(this).data('id');
	});
});