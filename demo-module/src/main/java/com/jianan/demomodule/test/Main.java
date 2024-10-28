package com.jianan.demomodule.test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest requert = HttpRequest.newBuilder()
                .GET().uri(URI.create("https://ug.baidu.com/mcp/pc/pcsearch")).build();
        try {
            HttpResponse<String> response = httpClient.send(requert, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("11"+"22222");
        
        
        
        int sum = 0;
        for(int i=1;i<=2;i++){
            int tem = 1;
            for (int j=1;j<=i;j++){
                tem = tem * j;
            }
            sum += tem;
        }
        System.out.println(sum);



        // (1) 创建一个整型数组 volunteerNeeds，表示各个志愿服务项目所需的志愿者数量
        int[] volunteerNeeds = {5, 3, 4, 2};

        // (2) 创建一个整型数组 communityVolunteers，用来表示各个社区报名参加志愿服务的居民数量
        int[] communityVolunteers = {4, 3, 2, 1, 2};

        // (3) 遍历所有志愿服务项目，并尝试从报名参加的居民中分配志愿者
        int totalVolunteers = 0;
        for (int num : communityVolunteers) {
            totalVolunteers += num;
        }

        int unassignedVolunteers = totalVolunteers;
        int[] assignedVolunteers = new int[volunteerNeeds.length];

        for (int i = 0; i < volunteerNeeds.length; i++) {
            int needed = volunteerNeeds[i];
            for (int j = 0; j < communityVolunteers.length && needed > 0; j++) {
                int available = communityVolunteers[j];
                if (available > 0) {
                    int toAssign = Math.min(available, needed);
                    communityVolunteers[j] -= toAssign;
                    assignedVolunteers[i] += toAssign;
                    needed -= toAssign;
                    unassignedVolunteers -= toAssign;
                }
            }
        }

        // (4) 输出未被分配的志愿者数量
        if (unassignedVolunteers > 0) {
            System.out.println("还有 " + unassignedVolunteers + " 个志愿者未被分配。");
        }

        // (5) 输出未满的项目及其所需志愿者数量
        for (int i = 0; i < volunteerNeeds.length; i++) {
            if (assignedVolunteers[i] < volunteerNeeds[i]) {
                int needed = volunteerNeeds[i] - assignedVolunteers[i];
                System.out.println("项目 " + (i + 1) + " 还需要 " + needed + " 个志愿者。");
            }
        }
    }
}
