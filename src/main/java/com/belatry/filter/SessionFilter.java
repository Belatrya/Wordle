package com.belatry.filter;

import com.belatry.model.Game;
import com.belatry.model.UserGames;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.MapSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
@Data
public class SessionFilter implements Filter {
    Game game;
    UserGames userGames;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        MapSession session = new MapSession();

        if (!userGames.isUserGameExist(session.getId())) {
            game.setUserName(session.getId());
        }

        filterChain.doFilter(request, response);
    }
}
