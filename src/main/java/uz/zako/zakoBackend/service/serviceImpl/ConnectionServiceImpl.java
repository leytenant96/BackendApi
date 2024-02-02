package uz.zako.zakoBackend.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.zako.zakoBackend.entity.Connection;
import uz.zako.zakoBackend.exeption.ResourceNotFound;
import uz.zako.zakoBackend.model.Result;
import uz.zako.zakoBackend.payload.ConnectionPayload;
import uz.zako.zakoBackend.repository.ConnectionRepository;
import uz.zako.zakoBackend.service.ConnectionService;

@Service
@RequiredArgsConstructor
@Slf4j

public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final Result result;
    @Override
    public Result saveConnection(ConnectionPayload connectionPayload) {
        try {
            Connection connection=Connection.builder()
                    .email(connectionPayload.getEmail())
                    .addressUz(connectionPayload.getAddressUz())
                    .addressRu(connectionPayload.getAddressRu())
                    .phoneNumber(connectionPayload.getPhoneNumber())
                    .build();
            connectionRepository.save(connection);
            return result.success(connection);
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

    @Override
    public Result deleteConnection(Long id) {
        try {
            Connection connection = connectionRepository.findById(id).orElseThrow();
            connectionRepository.delete(connection);
            return result.success("connection deleted");
        } catch (Exception e){
        log.error(e.getMessage());
        return result.failed  (e);
    }
    }

    @Override
    public Result editConnection(Long id, ConnectionPayload connectionPayload) {
        try{
            Connection connection =connectionRepository.findById(id).orElseThrow();
            if (connectionPayload.getPhoneNumber()!=(null)) connection.setPhoneNumber(connectionPayload.getPhoneNumber());
            if (connectionPayload.getEmail()!=(null)) connection.setEmail(connectionPayload.getEmail());
            if (connectionPayload.getAddressUz()!=(null)) connection.setAddressUz(connectionPayload.getAddressUz());
            if (connectionPayload.getAddressRu()!=(null)) connection.setAddressRu(connectionPayload.getAddressRu());
            connectionRepository.save(connection);
            return result.success(connection);}
        catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
        }
    @Override
    public Result getConnection() {
        try {
            return result.success(connectionRepository.findAll());
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

    @Override
    public Result findConnection(Long id) {
        try {
            Connection connection=connectionRepository.findById(id).orElseThrow(()->new ResourceNotFound("connection","id",id));
            return result.success(connection);
        }catch (Exception e){
            log.error(e.getMessage());
            return result.failed(e);
        }
    }

}
