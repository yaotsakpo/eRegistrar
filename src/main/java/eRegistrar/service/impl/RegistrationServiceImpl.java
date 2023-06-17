package eRegistrar.service.impl;

import eRegistrar.model.Registration;
import eRegistrar.repository.RegistrationRepository;
import eRegistrar.repository.StudentRepository;
import eRegistrar.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    RegistrationRepository registrationRepository;

    @Override
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public Registration saveRegistration(Registration Registration) {
        return registrationRepository.save(Registration);
    }

    @Override
    public Registration getRegistrationById(Integer RegistrationId) {
        return (registrationRepository.findById(RegistrationId)).orElse(null);
    }

    @Override
    public void deleteRegistrationById(Integer RegistrationId) {
        registrationRepository.deleteById(RegistrationId);
    }

    @Override
    public List<Registration> searchRegistrations(String searchString) {
        return null;
    }
}
