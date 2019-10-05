package com.java.codeChallenge.view;

import com.java.codeChallenge.service.OrganizationService;
import com.java.codeChallenge.service.TicketService;
import com.java.codeChallenge.service.UserService;
import com.java.codeChallenge.service.impl.OrganizationServiceImpl;
import com.java.codeChallenge.service.impl.TicketServiceImpl;
import com.java.codeChallenge.service.impl.UserServiceImpl;
import com.java.codeChallenge.storage.JsonObjectStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.java.codeChallenge.enums.OptionsEnum.MainOption.*;
import static com.java.codeChallenge.enums.OptionsEnum.SearchOptions.ORGANIZATIONS;
import static com.java.codeChallenge.enums.OptionsEnum.SearchOptions.TICKETS;
import static com.java.codeChallenge.enums.OptionsEnum.SearchOptions.USERS;

public class ConsoleOutput implements ICodeChallengeOutput {

    private UserService userService;
    private TicketService ticketService;
    private OrganizationService organizationService;
    public ConsoleOutput(){
        userService = new UserServiceImpl();
        ticketService = new TicketServiceImpl();
        organizationService = new OrganizationServiceImpl();
    }
    @Override
    public void create() {
        Scanner scanner = new Scanner(System.in);
        heading();
        quietOrEnter();
        mainSearchOptions(scanner);
    }

    private void mainSearchOptions(Scanner scanner) {
        System.out.print("\n");
        System.out.println("            Select search options:");
        System.out.println("            * Press 1 to search");
        System.out.println("            * Press 2 to view a list of searchable fields");
        System.out.println("            * Type 'quite' to exit");
        String option = scanner.nextLine();
        if(SEARCH.value.equals(option)){
            searchOption(scanner);
        } else if (VIEW.value.equals(option)){

        } else if (QUIET.value.equalsIgnoreCase(option)){
            scanner.close();
            return;
        } else
            mainSearchOptions(scanner);


    }

    private void searchOption(Scanner scanner) {
        System.out.println("Select 1) Users or 2) Tickets or 3)Organizations");
        String option = scanner.nextLine();
        if(USERS.value.equals(option)){
            startUserSearch(scanner);
        } else if (TICKETS.value.equals(option)){
            startTicketSearch(scanner);
        } else if (ORGANIZATIONS.value.equals(option)){
            startOrganizationSearch(scanner);
        } else {
            searchOption(scanner);
        }

    }

    private void startTicketSearch(Scanner scanner) {
        Map<String,String> searchValues = getSearchCriteria(scanner, new HashMap());
        ticketService.displayResultsByCriteria(searchValues);
    }

    private void startUserSearch(Scanner scanner) {
        Map<String,String> searchValues = getSearchCriteria(scanner, new HashMap());
        userService.displayResultsByCriteria(searchValues);
    }

    private void startOrganizationSearch(Scanner scanner) {
        Map<String,String> searchValues = getSearchCriteria(scanner, new HashMap());
        organizationService.displayResultsByCriteria(searchValues);
    }

    private Map<String, String> getSearchCriteria(Scanner scanner, Map map) {
        if(map.isEmpty()) {
            setSearchEntry(scanner, map);
            return getSearchCriteria(scanner,map);
        } else {
            System.out.println("Select 1) Add another search term or 2) Continue Search");
            String option = scanner.nextLine();
            if(ANOTHER_TERM.value.equals(option)){
                setSearchEntry(scanner, map);
                return getSearchCriteria(scanner,map);
            } else if(CONTINUE_SEARCH.value.equals(option)){
                return map;
            }

        }
        return null;
    }

    private void quietOrEnter() {
        System.out.println("Type 'quite' to exit at any time, press 'enter' to continue");
    }

    private void heading() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< Hello Welcome to Code Challenge >>>>>>>>>>>>>>>>>>>");
    }

    public void setSearchEntry(Scanner scanner, Map map) {
        System.out.println("Enter search term");
        String searchTerm = scanner.nextLine();
        System.out.println("Enter search value");
        String searchValue = scanner.nextLine();
        map.put(searchTerm,searchValue);
    }
}
