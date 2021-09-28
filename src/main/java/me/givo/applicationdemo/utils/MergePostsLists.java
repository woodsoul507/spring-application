package me.givo.applicationdemo.utils;

import me.givo.applicationdemo.models.PostElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class MergePostsLists {

    public PostElement[] mergeArrays(PostElement[] firstArray, PostElement[] secondArray) {

        int firstArrayLength = firstArray.length;
        int secondArrayLength = secondArray.length;

        ArrayList<PostElement> tempList = new ArrayList<>();

        tempList.addAll(Arrays.asList(firstArray).subList(0, firstArrayLength));

        tempList.addAll(Arrays.asList(secondArray).subList(0, secondArrayLength));

        Set tempSet = new HashSet(tempList);

        PostElement[] resultArray = new PostElement[tempSet.size()];
        
        for (int i = 0; i < tempSet.size(); i++) {
            resultArray[i] = (PostElement) tempSet.toArray()[i];
        }

        return resultArray;
    }


}
