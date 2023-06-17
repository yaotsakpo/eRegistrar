package eRegistrar.service.impl;

import eRegistrar.model.Block;
import eRegistrar.repository.BlockRepository;
import eRegistrar.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    BlockRepository blockRepository;

    @Override
    public List<Block> getAllBlocks() {
        return blockRepository.findAll();
    }

    @Override
    public Block saveBlock(Block Block) {
        return blockRepository.save(Block);
    }

    @Override
    public Block getBlockById(Integer BlockId) {
        return (blockRepository.findById(BlockId)).orElse(null);
    }

    @Override
    public void deleteBlockById(Integer BlockId) {
            blockRepository.deleteById(BlockId);
    }

    @Override
    public List<Block> searchBlocks(String searchString) {
        return null;
    }
}
