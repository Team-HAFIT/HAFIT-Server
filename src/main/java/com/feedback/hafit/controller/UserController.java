package com.feedback.hafit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feedback.hafit.entity.ScoreDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    final String JDBC_URL = "jdbc:mariadb://localhost:3305/hafit_db";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null; // 쿼리결과셋 객체 변수

    @PostMapping("submit")
    public ModelAndView submit(@RequestParam("data") String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ScoreDTO score = objectMapper.readValue(data, ScoreDTO.class);
            boolean rs = insertInfo(score.getCOUNT(), score.getSCORE(), score.getBAD());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @PostMapping("setexec")
    public ModelAndView set(@RequestParam("repsPerSet") int repsPerSet,
                            @RequestParam("totalSets") int totalSets,
                            @RequestParam("weight") int weight,
                            @RequestParam("restTime") int restTime,
                            Model model) {
        model.addAttribute("repsPerSet", repsPerSet);
        model.addAttribute("totalSets", totalSets);
        model.addAttribute("weight", weight);
        model.addAttribute("restTime", restTime);
        ModelAndView mav = new ModelAndView("setExec");
        return mav;
    }

    @GetMapping("exec")
    public ModelAndView exec() {
        ModelAndView mav = new ModelAndView("exec");
        return mav;
    }

    @GetMapping("setting")
    public ModelAndView setting() {
        ModelAndView mav = new ModelAndView("setting");
        return mav;
    }

    @GetMapping("/")
    public ModelAndView mainP() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @GetMapping("updateP")
    public ModelAndView updateP() {
        ModelAndView mav = new ModelAndView("update");
        return mav;
    }

    @GetMapping("deleteP")
    public ModelAndView deleteP(HttpServletRequest request) {
        String pw = request.getParameter("USER_PW");
        ModelAndView mav = new ModelAndView("delete");
        return mav;
    }

    //    @CrossOrigin(origins = "http://192.168.0.45:3000")
    @PostMapping("/update")
    public ModelAndView update(HttpServletRequest request) {
        String id = request.getParameter("USER_ID");
        String pw = request.getParameter("USER_PW");
        String name = request.getParameter("USER_NAME");
        String nickname = request.getParameter("USER_NICKNAME");
        String tel = request.getParameter("USER_TEL");
        boolean rs = updateInfo(name, id, pw, nickname, tel);
        ModelAndView mav;
        if (rs == true) {
            mav = new ModelAndView("updateSP");
        } else {
            mav = new ModelAndView("index");
        }
        return mav;
    }

    @PostMapping("delete")
    public ModelAndView delete(HttpServletRequest request) {
        String pw = request.getParameter("USER_PW");
        boolean rs = deleteInfo(pw);
        ModelAndView mav;
        if (rs == true) {
            mav = new ModelAndView("deleteSP");
        } else {
            mav = new ModelAndView("index");
        }
        return mav;
    }

    public void open() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, "root", "root");

            System.out.println("------------ # DB연결 성공! # ------------");
        } catch (Exception e) {
            System.out.println("------------ # DB연결 실패! # ------------");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean insertInfo(int count, int score, int bad) {
        open();
        String SQL = "INSERT INTO scoredata (COUNT, SCORE, BAD) VALUE (?, ?, ?)";

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, count);
            pstmt.setInt(2, score);
            pstmt.setInt(3, bad);
            int resultSet = pstmt.executeUpdate();
            if (resultSet > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

    public boolean updateInfo(String name, String id, String pw, String nickname, String tel) {
        open();
        String SQL = "UPDATE user SET USER_NAME=?, USER_ID=?, USER_PW=?, USER_NICKNAME=?, USER_TEL=? WHERE USER_NO = 1";

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, name);
            pstmt.setString(2, id);
            pstmt.setString(3, pw);
            pstmt.setString(4, nickname);
            pstmt.setString(5, tel);
            int resultSet = pstmt.executeUpdate();
            if (resultSet > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

    public boolean deleteInfo(String pw) {
        open();
        String SQL = "UPDATE user SET USER_STATUS = ?";
        SQL += " WHERE USER_PW = ?";

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, "n");
            pstmt.setString(2, pw);
            int resultSet = pstmt.executeUpdate();
            if (resultSet > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

}