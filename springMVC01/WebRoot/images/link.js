//div_path = "/justice/";
div_path = "";



cv_nonsc_base_path = "http:" + "//" + "www.doj.gov.hk";

path = cv_sc_base_path + div_path;

tc_path = cv_nonsc_base_path + div_path;
front_path = cv_nonsc_base_path + div_path;

if (location.href.toString().search('/gb/') != -1) {
	atgb = 1
} else {
	atgb = 0;
}

function changelanuage(lang) {

        chi_path = "chi/";
        eng_path = "eng/";

	org_path=window.location.pathname;
	org_path_start=org_path.indexOf(div_path);
	org_path_end=org_path.length;
	org_path=org_path.substring(org_path_start, org_path_end);

	if (org_path.indexOf(chi_path) != -1) {
		org_lang_path = chi_path;
	} else {
		org_lang_path = eng_path;
	}

	if (lang == 'eng') {
		des_lang_path = eng_path;
	} else {
		des_lang_path = chi_path;
	}
	
		

	tail = org_path.replace(org_lang_path, des_lang_path);
	
	alert(base_path);
	alert(tail);
	alert(base_path + tail);

	window.location.href = base_path + tail;
}
