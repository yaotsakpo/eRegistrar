package eRegistrar.service;

import eRegistrar.model.Registration;

import java.util.List;

public interface RegistrationService {

    public abstract List<Registration> getAllRegistrations();
    public abstract Registration saveRegistration(Registration Registration);
    public abstract Registration getRegistrationById(Integer RegistrationId);
    public abstract void deleteRegistrationById(Integer RegistrationId);
    public abstract List<Registration> searchRegistrations(String searchString);
}
