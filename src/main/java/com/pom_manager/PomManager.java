package com.pom_manager;

import com.airasia_pages.LogInPage;

public class PomManager {
private LogInPage loginpage;

public LogInPage getLoginpage() {
	return (loginpage ==null)?loginpage = new LogInPage():loginpage;
}
	

}
