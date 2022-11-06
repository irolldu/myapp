$(post.description).summernote({
	lang: "ko-KR",
	height: 300,
	callbacks: {
		onInit: description => {
			if (post.description.classList.contains("is-valid")) {
				description.editor[0].classList.add("is-valid");
			} else if (post.description.classList.contains("is-invalid")) {
				description.editor[0].classList.add("is-invalid");
			}
		},
		onImageUpload: files => {
			const formData = new FormData();
			formData.append("file", files[0]);
			
			fetch(post.dataset.image, {
				method: "post",
				body: formData
			}).then(response => {
				return response.text();
			}).then(data => {
				$(post.description).summernote("insertNode", $("<img>").attr("src", data)[0]);
			});
		}
	}
});

post.image[1].onchange = () => {
	const formData = new FormData();
	formData.append("file", post.image[1].files[0]);
	
	fetch(post.dataset.image, {
		method: "post",
		body: formData
	}).then(function(response) {
		return response.text();
	}).then(function(data) {
		post.image[0].value = data;
	});
};

post.onsubmit = () => {
	event.preventDefault();
	post.files.disabled = true;
	post.submit();
}