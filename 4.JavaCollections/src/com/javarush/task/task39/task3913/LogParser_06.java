package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser_06 implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<Logs> logs = new ArrayList<>();
    private DateFormat simpleDateFormat = new SimpleDateFormat("d.M.yyyy H:m:s");

    public LogParser_06(Path logDir) {
        this.logDir = logDir;
        readLogs();
    }

    private void readLogs() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(logDir)) {
            for (Path file : directoryStream) {
                if (file.toString().toLowerCase().endsWith(".log")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] params = line.split("\t");

                            if (params.length != 5) {
                                continue;
                            }

                            String ip = params[0];
                            String user = params[1];
                            Date date = readDate(params[2]);
                            Event event = readEvent(params[3]);
                            int eventAdditionalParameter = -1;
                            if (event.equals(Event.SOLVE_TASK) || event.equals(Event.DONE_TASK)) {
                                eventAdditionalParameter = readAdditionalParameter(params[3]);
                            }
                            Status status = readStatus(params[4]);

                            Logs logs = new Logs(ip, user, date, event, eventAdditionalParameter, status);
                            this.logs.add(logs);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Date readDate(String lineToParse) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(lineToParse);
        } catch (ParseException ignored) {
        }
        return date;
    }

    private Event readEvent(String lineToParse) {
        Event event = null;
        if (lineToParse.contains("SOLVE_TASK")) {
            event = Event.SOLVE_TASK;
        } else if (lineToParse.contains("DONE_TASK")) {
            event = Event.DONE_TASK;
        } else {
            switch (lineToParse) {
                case "LOGIN": {
                    event = Event.LOGIN;
                    break;
                }
                case "DOWNLOAD_PLUGIN": {
                    event = Event.DOWNLOAD_PLUGIN;
                    break;
                }
                case "WRITE_MESSAGE": {
                    event = Event.WRITE_MESSAGE;
                    break;
                }
            }
        }
        return event;
    }

    private int readAdditionalParameter(String lineToParse) {
        if (lineToParse.contains("SOLVE_TASK")) {
            lineToParse = lineToParse.replace("SOLVE_TASK", "").replaceAll(" ", "");
        } else {
            lineToParse = lineToParse.replace("DONE_TASK", "").replaceAll(" ", "");
        }
        return Integer.parseInt(lineToParse);
    }

    private Status readStatus(String lineToParse) {
        Status status = null;
        switch (lineToParse) {
            case "OK": {
                status = Status.OK;
                break;
            }
            case "FAILED": {
                status = Status.FAILED;
                break;
            }
            case "ERROR": {
                status = Status.ERROR;
                break;
            }
        }
        return status;
    }

    private boolean dateBetweenDates(Date current, Date after, Date before) {
        if (after == null) {
            after = new Date(0);
        }
        if (before == null) {
            before = new Date(Long.MAX_VALUE);
        }
        return current.after(after) && current.before(before);
    }

    private class Logs {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int eventAdditionalParameter;
        private Status status;

        public Logs(String ip, String user, Date date, Event event, int eventAdditionalParameter, Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.eventAdditionalParameter = eventAdditionalParameter;
            this.status = status;
        }

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public int getEventAdditionalParameter() {
            return eventAdditionalParameter;
        }

        public Status getStatus() {
            return status;
        }
    }


    /*
    IPQuery implementation
    */

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                result.add(log.getIp());
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getUser().equals(user)) {
                    result.add(log.getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(event)) {
                    result.add(log.getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getStatus().equals(status)) {
                    result.add(log.getIp());
                }
            }
        }
        return result;
    }

    /*
    UserQuery implementation
    */

    @Override
    public Set<String> getAllUsers() {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            result.add(log.getUser());
        }
        return result;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                result.add(log.getUser());
            }
        }
        return result.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getUser().equals(user)) {
                    result.add(log.getEvent());
                }
            }
        }
        return result.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getIp().equals(ip)) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(Event.LOGIN)) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(Event.DOWNLOAD_PLUGIN)) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(Event.WRITE_MESSAGE)) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(Event.SOLVE_TASK)) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(Event.SOLVE_TASK)
                        && log.getEventAdditionalParameter() == task) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(Event.DONE_TASK)) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(Event.DONE_TASK)
                        && log.getEventAdditionalParameter() == task) {
                    result.add(log.getUser());
                }
            }
        }
        return result;
    }

    /*
    DateQuery implementation
     */
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(event)
                        && log.getUser().equals(user)) {
                    result.add(log.getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getStatus().equals(Status.FAILED)) {
                    result.add(log.getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getStatus().equals(Status.ERROR)) {
                    result.add(log.getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        List<Date> result = new ArrayList<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getUser().equals(user) && log.getEvent().equals(Event.LOGIN)) {
                    result.add(log.getDate());
                }
            }
        }
        if (result.isEmpty()) return null;
        Collections.sort(result);
        return result.get(0);
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        List<Date> result = new ArrayList<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getUser().equals(user)
                        && log.getEvent().equals(Event.SOLVE_TASK)
                        && log.getEventAdditionalParameter() == task) {
                    result.add(log.getDate());
                }
            }
        }
        if (result.isEmpty()) return null;
        Collections.sort(result);
        return result.get(0);
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        List<Date> result = new ArrayList<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getUser().equals(user)
                        && log.getEvent().equals(Event.DONE_TASK)
                        && log.getEventAdditionalParameter() == task) {
                    result.add(log.getDate());
                }
            }
        }
        if (result.isEmpty()) return null;
        Collections.sort(result);
        return result.get(0);
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getUser().equals(user) && log.getEvent().equals(Event.WRITE_MESSAGE)) {
                    result.add(log.getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getUser().equals(user) && log.getEvent().equals(Event.DOWNLOAD_PLUGIN)) {
                    result.add(log.getDate());
                }
            }
        }
        return result;
    }

    /*
    EventQuery implementation
     */

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                result.add(log.getEvent());
            }
        }
        return result;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getIp().equals(ip)) {
                    result.add(log.getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getUser().equals(user)) {
                    result.add(log.getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getStatus().equals(Status.FAILED)) {
                    result.add(log.getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getStatus().equals(Status.ERROR)) {
                    result.add(log.getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int result = 0;
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(Event.SOLVE_TASK)
                        && log.getEventAdditionalParameter() == task) {
                    result++;
                }
            }
        }
        return result;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int result = 0;
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(Event.DONE_TASK)
                        && log.getEventAdditionalParameter() == task) {
                    result++;
                }
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(Event.SOLVE_TASK)) {
                    int task = log.getEventAdditionalParameter();
                    result.put(task, getNumberOfAttemptToSolveTask(task, after, before));
                }
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Logs log : logs) {
            if (dateBetweenDates(log.getDate(), after, before)) {
                if (log.getEvent().equals(Event.DONE_TASK)) {
                    int task = log.getEventAdditionalParameter();
                    result.put(task, getNumberOfSuccessfulAttemptToSolveTask(task, after, before));
                }
            }
        }
        return result;
    }

    /*
    QLQuery implementation
     */
    @Override
    public Set<Object> execute(String query) {
        String[] querySplit = query.split(" ");
        if (querySplit.length == 2) {
            switch (querySplit[1]) {
                case "ip": {
                    return new HashSet<>(getUniqueIPs(null, null));
                }
                case "user": {
                    return new HashSet<>(getAllUsers());
                }
                case "date": {
                    Set<Object> result = new HashSet<>();
                    for (LogParser_06.Logs log : logs) {
                        result.add(log.getDate());
                    }
                    return result;
                }
                case "event": {
                    return new HashSet<>(getAllEvents(null, null));
                }
                case "status": {
                    Set<Object> result = new HashSet<>();
                    for (LogParser_06.Logs log : logs) {
                        result.add(log.getStatus());
                    }
                    return result;
                }
                default:
                    return new HashSet<>();
            }
        }
        String value1 = query.split("=")[1].replaceAll("\"","").trim();
        switch (querySplit[1]) {
            case "ip" : {
                switch (querySplit[3]) {
                    case "user" : {
                        return new HashSet<>(getIPsForUser(value1, null, null));
                    }
                    case "date" : {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            try {
                                Date date = simpleDateFormat.parse(value1);
                                if (date.equals(log.getDate())) {
                                    result.add(log.getIp());
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        return result;
                    }
                    case "event" : {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String event = String.valueOf(log.getEvent());
                            if (event.equals(value1)) {
                                result.add(log.getIp());
                            }
                        }
                        return result;
                    }
                    case "status" : {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String status = String.valueOf(log.getStatus());
                            if (status.equals(value1)) {
                                result.add(log.getIp());
                            }
                        }
                        return result;
                    }
                }
            }
            case "user" : {
                switch (querySplit[3]) {
                    case "ip": {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String ip = String.valueOf(log.getIp());
                            if (ip.equals(value1)) {
                                result.add(log.getUser());
                            }
                        }
                        return result;
                    }
                    case "date" : {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            try {
                                Date date = simpleDateFormat.parse(value1);
                                if (date.equals(log.getDate())) {
                                    result.add(log.getUser());
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                        return result;
                    }
                    case "event": {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String event = String.valueOf(log.getEvent());
                            if (event.equals(value1)) {
                                result.add(log.getUser());
                            }
                        }
                        return result;
                    }
                    case "status" : {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String status = String.valueOf(log.getStatus());
                            if (status.equals(value1)) {
                                result.add(log.getUser());
                            }
                        }
                        return result;
                    }
                }
            }
            case "date" : {
                switch (querySplit[3]) {
                    case "ip": {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String ip = String.valueOf(log.getIp());
                            if (ip.equals(value1)) {
                                result.add(log.getDate());
                            }
                        }
                        return result;
                    }
                    case "user": {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String ip = String.valueOf(log.getUser());
                            if (ip.equals(value1)) {
                                result.add(log.getDate());
                            }
                        }
                        return result;
                    }
                    case "event": {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String event = String.valueOf(log.getEvent());
                            if (event.equals(value1)) {
                                result.add(log.getDate());
                            }
                        }
                        return result;
                    }
                    case "status" : {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String status = String.valueOf(log.getStatus());
                            if (status.equals(value1)) {
                                result.add(log.getDate());
                            }
                        }
                        return result;
                    }
                }
            }
            case "event" : {
                switch (querySplit[3]) {
                    case "ip": {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String ip = String.valueOf(log.getIp());
                            if (ip.equals(value1)) {
                                result.add(log.getEvent());
                            }
                        }
                        return result;
                    }
                    case "user": {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String ip = String.valueOf(log.getUser());
                            if (ip.equals(value1)) {
                                result.add(log.getEvent());
                            }
                        }
                        return result;
                    }
                    case "date" : {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            try {
                                Date date = simpleDateFormat.parse(value1);
                                if (date.equals(log.getDate())) {
                                    result.add(log.getEvent());
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        return result;
                    }
                    case "status" : {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String status = String.valueOf(log.getStatus());
                            if (status.equals(value1)) {
                                result.add(log.getEvent());
                            }
                        }
                        return result;
                    }
                }
            }
            case "status" : {
                switch (querySplit[3]) {
                    case "ip": {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String ip = String.valueOf(log.getIp());
                            if (ip.equals(value1)) {
                                result.add(log.getStatus());
                            }
                        }
                        return result;
                    }
                    case "user": {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String ip = String.valueOf(log.getUser());
                            if (ip.equals(value1)) {
                                result.add(log.getStatus());
                            }
                        }
                        return result;
                    }
                    case "date" : {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            try {
                                Date date = simpleDateFormat.parse(value1);
                                if (date.equals(log.getDate())) {
                                    result.add(log.getStatus());
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        return result;
                    }
                    case "event": {
                        Set<Object> result = new HashSet<>();
                        for (Logs log : logs) {
                            String event = String.valueOf(log.getEvent());
                            if (event.equals(value1)) {
                                result.add(log.getStatus());
                            }
                        }
                        return result;
                    }
                }
            }
            default:
                return new HashSet<>();
        }
    }
}
