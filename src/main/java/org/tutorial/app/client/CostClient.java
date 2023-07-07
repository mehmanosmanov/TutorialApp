package org.tutorial.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.tutorial.app.dto.internal.TutorialCost;

/**
 * @author Mehman on 07-07-2023
 * @project tutorialApp
 */
@FeignClient(name = "simpleClient", url = "http://localhost:8090")
public interface CostClient {

    @GetMapping("/cost/data/{name}")
    TutorialCost getData(@PathVariable String name);

    @PostMapping("/cost/data")
    void postData(@RequestBody TutorialCost cost);

}

