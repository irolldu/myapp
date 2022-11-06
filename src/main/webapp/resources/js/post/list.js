list.onchange = () => {
	list.submit();
}

list.orderBy_field.onchange = () => {
	switch (list.orderBy_field.value) {
	case "ID":
		list.orderBy_direction.value = "DESC"
		break;
	case "ENDDATE":
		list.orderBy_direction.value = "ASC"
		break;
	}
}