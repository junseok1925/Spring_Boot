const btn = document.querySelector('.delete');

btn.addEventListener('click', function(){
	
	if(confirm('삭제하기겠습니까?')){
		location.href = this.dataset.uri;
	}
	
});