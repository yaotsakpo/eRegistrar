package eRegistrar.service;

import eRegistrar.model.Block;

import java.util.List;

public interface BlockService {
    public abstract List<Block> getAllBlocks();
    public abstract Block saveBlock(Block Block);
    public abstract Block getBlockById(Integer BlockId);
    public abstract void deleteBlockById(Integer BlockId);
    public abstract List<Block> searchBlocks(String searchString);
}
