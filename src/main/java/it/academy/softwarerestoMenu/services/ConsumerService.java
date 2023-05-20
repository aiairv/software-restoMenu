package it.academy.softwarerestoMenu.services;


import it.academy.softwarerestoMenu.exceptions.ConsumerNotFoundException;
import it.academy.softwarerestoMenu.model.Consumer;
import it.academy.softwarerestoMenu.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    public Consumer createUser(Consumer consumer) {
        return consumerRepository.save(consumer);
    }

    public Consumer getUserById(Long id) {
        return consumerRepository.findById(id).orElseThrow(ConsumerNotFoundException::new);

    }

    public Consumer updateUser(Consumer user) {
        Consumer existingUser = consumerRepository.findById(user.getId())
                .orElseThrow(ConsumerNotFoundException::new);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return consumerRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        Consumer user = consumerRepository.findById(id)
                . orElseThrow(ConsumerNotFoundException::new);
        consumerRepository.delete(user);
    }

}

