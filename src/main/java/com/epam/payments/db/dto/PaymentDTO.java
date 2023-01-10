package com.epam.payments.db.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PaymentDTO implements Serializable {
    private static final long serialVersionUID = 4999252687759877171L;
    
    private Long id;
    private Long sender_wallet_id;
    private String game_name;
    private String game_nickname;
    private LocalDateTime date_time;
    private double sum;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, Long sender_wallet_id, String game_name, String game_nickname, LocalDateTime date_time, double sum) {
        this.id = id;
        this.sender_wallet_id = sender_wallet_id;
        this.game_name = game_name;
        this.game_nickname = game_nickname;
        this.date_time = date_time;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", sender_wallet_id=" + sender_wallet_id +
                ", game_name='" + game_name + '\'' +
                ", game_nickname='" + game_nickname + '\'' +
                ", date_time=" + date_time +
                ", sum=" + sum +
                '}';
    }
}
