package com.john.chess;

import com.john.chess.Engine.Board.Move;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChessController {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name="name", required = false, defaultValue="World") String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

        @MessageMapping("/chat.sendMessage")
        @SendTo("/topic/public")
        public Move sendMessage(@Payload Move message){
            return message;
        }

}
