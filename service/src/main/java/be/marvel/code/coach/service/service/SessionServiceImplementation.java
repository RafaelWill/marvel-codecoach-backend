package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.Session;
import be.marvel.code.coach.domain.repository.SessionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SessionServiceImplementation implements SessionService{

    private final SessionRepository sessionRepository;

    public SessionServiceImplementation(SessionRepository sessionRepository){
        this.sessionRepository = sessionRepository;
    }

    public Session save(Session session){
        return sessionRepository.save(session);
    }
}
