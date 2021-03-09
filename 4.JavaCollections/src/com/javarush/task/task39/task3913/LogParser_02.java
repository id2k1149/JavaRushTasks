package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class LogParser_02 implements IPQuery, UserQuery {
    private List<Logs> logs;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private class Logs {
        String ip;
        String user;
        Date date;
        Event event;
        Integer taskNumber;
        Status status;

        @Override
        public String toString() {
            return "Record{" +
                    "ip='" + ip + '\'' +
                    ", user='" + user + '\'' +
                    ", date=" + date +
                    ", event=" + event +
                    ", taskNumber=" + taskNumber +
                    ", status=" + status +
                    '}';
        }
    }

    public LogParser_02(Path logDir) {
        logs = new ArrayList<>();

        readLogs(logDir);
    }

    private void readLogs(Path logDir) {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(logDir)) {
            for (Path log : directoryStream) {
                if (Files.isRegularFile(log) && log.toString().endsWith(".log")) {
                    BufferedReader reader = Files.newBufferedReader(log, StandardCharsets.UTF_8);
                    while (reader.ready()) {
                        Logs record = new Logs();
                        String[] entry = reader.readLine().split("\t");
                        record.ip = entry[0];
                        record.user = entry[1];
                        record.date = formatter.parse(entry[2]);

                        if (entry[3].indexOf(' ') == -1) {
                            record.event = Event.valueOf(entry[3]);
                            record.taskNumber = null;
                        } else {
                            String[] event = entry[3].split(" ");
                            record.event = Event.valueOf(event[0]);
                            record.taskNumber = Integer.parseInt(event[1]);
                        }

                        record.status = Status.valueOf(entry[4]);

                        logs.add(record);
                    }
                    reader.close();
                } else {
                    if (Files.isDirectory(log)) {
                        readLogs(log);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private Set<Logs> getFilteredEntries(Date after, Date before) {

        Set<Logs> filteredRecords = new HashSet<>();
        for (Logs record : logs) {
            if (isBetween(record.date, after, before)) {
                filteredRecords.add(record);
            }
        }

        return filteredRecords;
    }

    private boolean isBetween(Date date, Date after, Date before) {
        return (after == null || date.after(after) || date.equals(after)) &&
                (before == null || date.before(before) || date.equals(before));
    }

    /*
    IPQuery implementation
    */

    @Override
    // должен возвращать количество уникальных IP адресов за выбранный период
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> ips = getUniqueIPs(after, before);
        return ips.size();
    }

    @Override
    // должен возвращать множество, содержащее все не повторяющиеся IP
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<Logs> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Logs record : filteredRecords) {
            ips.add(record.ip);
        }

        return ips;
    }

    @Override
    // должен возвращать IP, с которых работал переданный пользователь.
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<Logs> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Logs record : filteredRecords) {
            if (record.user.equals(user))
                ips.add(record.ip);
        }

        return ips;
    }

    @Override
    // должен возвращать IP, с которых было произведено переданное событие.
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<Logs> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Logs record : filteredRecords) {
            if (record.event.equals(event))
                ips.add(record.ip);
        }

        return ips;
    }

    @Override
    // должен возвращать IP, события с которых закончилось переданным статусом.
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<Logs> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Logs record : filteredRecords) {
            if (record.status.equals(status))
                ips.add(record.ip);
        }

        return ips;
    }

/*
    UserQuery implementation
*/

    @Override
    // Метод должен возвращать множество содержащее всех пользователей.
    public Set<String> getAllUsers() {
        return logs
                .stream()
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return (int) logs
                .stream()
                .filter(log -> (after == null || (log.date.getTime() >= after.getTime())) && (before == null || log.date.getTime() <= before.getTime()))
                .map(log -> log.user)
                .distinct()
                .count();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return (int) logs
                .stream()
                .filter(log -> log.user.equals(user))
                .filter(log -> (after == null || (log.date.getTime() >= after.getTime())) && (before == null || log.date.getTime() <= before.getTime()))
                .map(log -> log.event)
                .distinct()
                .count();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return logs
                .stream()
                .filter(log -> (after == null || (log.date.getTime() >= after.getTime())) && (before == null || log.date.getTime() <= before.getTime()))
                .filter(log -> log.ip.equals(ip))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return logs
                .stream()
                .filter(log -> (after == null || (log.date.getTime() >= after.getTime())) && (before == null || log.date.getTime() <= before.getTime()))
                .filter(log -> log.event.equals(Event.LOGIN))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return logs
                .stream()
                .filter(log -> (after == null || (log.date.getTime() >= after.getTime())) && (before == null || log.date.getTime() <= before.getTime()))
                .filter(log -> log.event.equals(Event.DOWNLOAD_PLUGIN))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return logs
                .stream()
                .filter(log -> (after == null || (log.date.getTime() >= after.getTime())) && (before == null || log.date.getTime() <= before.getTime()))
                .filter(log -> log.event.equals(Event.WRITE_MESSAGE))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return logs
                .stream()
                .filter(log -> (after == null || (log.date.getTime() >= after.getTime())) && (before == null || log.date.getTime() <= before.getTime()))
                .filter(log -> log.event.equals(Event.SOLVE_TASK))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return logs
                .stream()
                .filter(log -> log.event.equals(Event.SOLVE_TASK))
                .filter(log -> log.taskNumber.equals(task))
                .filter(log -> (after == null || (log.date.getTime() >= after.getTime())) && (before == null || log.date.getTime() <= before.getTime()))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return logs
                .stream()
                .filter(log -> (after == null || (log.date.getTime() >= after.getTime())) && (before == null || log.date.getTime() <= before.getTime()))
                .filter(log -> log.event.equals(Event.DONE_TASK))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return logs
                .stream()
                .filter(log -> log.event.equals(Event.DONE_TASK))
                .filter(log -> log.taskNumber.equals(task))
                .filter(log -> (after == null || (log.date.getTime() >= after.getTime())) && (before == null || log.date.getTime() <= before.getTime()))
                .map(log -> log.user)
                .collect(Collectors.toSet());
    }

}