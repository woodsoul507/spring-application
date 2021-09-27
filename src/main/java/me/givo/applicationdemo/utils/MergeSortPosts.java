package me.givo.applicationdemo.utils;

import me.givo.applicationdemo.models.PostElement;
import org.springframework.stereotype.Component;

@Component
public class MergeSortPosts {

    void mergeArray(PostElement[] unsortedArray,
                    int start,
                    int middle,
                    int end,
                    String sortBy,
                    String direction) {

        int left = middle - start + 1;
        int right = end - middle;

        PostElement[] leftArray = new PostElement[left];
        PostElement[] rightArray = new PostElement[right];

        for (int x = 0; x < left; ++x) {
            leftArray[x] = unsortedArray[start + x];

        }

        for (int x = 0; x < right; ++x) {
            rightArray[x] = unsortedArray[middle + 1 + x];

        }

        int x = 0;
        int y = 0;
        int z = start;

        // Sort by Popularity
        if (sortBy.equals("popularity")) {
            if (direction.equals("desc")) {
                while (x < left && y < right) {
                    if (leftArray[x].getPopularity() >= rightArray[y].getPopularity()) {
                        unsortedArray[z] = leftArray[x];
                        x++;
                    } else {
                        unsortedArray[z] = rightArray[y];
                        y++;
                    }
                    z++;
                }
            } else {
                while (x < left && y < right) {
                    if (leftArray[x].getPopularity() <= rightArray[y].getPopularity()) {
                        unsortedArray[z] = leftArray[x];
                        x++;
                    } else {
                        unsortedArray[z] = rightArray[y];
                        y++;
                    }
                    z++;
                }
            }
        }
        // Sort by Likes
        else if (sortBy.equals("likes")) {
            if (direction.equals("desc")) {
                while (x < left && y < right) {
                    if (leftArray[x].getLikes() >= rightArray[y].getLikes()) {
                        unsortedArray[z] = leftArray[x];
                        x++;
                    } else {
                        unsortedArray[z] = rightArray[y];
                        y++;
                    }
                    z++;
                }
            } else {
                while (x < left && y < right) {
                    if (leftArray[x].getLikes() <= rightArray[y].getLikes()) {
                        unsortedArray[z] = leftArray[x];
                        x++;
                    } else {
                        unsortedArray[z] = rightArray[y];
                        y++;
                    }
                    z++;
                }
            }
        }
        // Sort by Reads
        else if (sortBy.equals("reads")) {
            if (direction.equals("desc")) {
                while (x < left && y < right) {
                    if (leftArray[x].getReads() >= rightArray[y].getReads()) {
                        unsortedArray[z] = leftArray[x];
                        x++;
                    } else {
                        unsortedArray[z] = rightArray[y];
                        y++;
                    }
                    z++;
                }
            } else {
                while (x < left && y < right) {
                    if (leftArray[x].getReads() <= rightArray[y].getReads()) {
                        unsortedArray[z] = leftArray[x];
                        x++;
                    } else {
                        unsortedArray[z] = rightArray[y];
                        y++;
                    }
                    z++;
                }
            }
        }
        // Sort by Id
        else {
            if (direction.equals("desc")) {
                while (x < left && y < right) {
                    if (leftArray[x].getId() >= rightArray[y].getId()) {
                        unsortedArray[z] = leftArray[x];
                        x++;
                    } else {
                        unsortedArray[z] = rightArray[y];
                        y++;
                    }
                    z++;
                }
            } else {
                while (x < left && y < right) {
                    if (leftArray[x].getId() <= rightArray[y].getId()) {
                        unsortedArray[z] = leftArray[x];
                        x++;
                    } else {
                        unsortedArray[z] = rightArray[y];
                        y++;
                    }
                    z++;
                }
            }
        }
        while (x < left) {
            unsortedArray[z] = leftArray[x];
            x++;
            z++;
        }

        while (y < right) {
            unsortedArray[z] = rightArray[y];
            y++;
            z++;
        }
    }

    public void sortArray(PostElement[] arr,
                          int start,
                          int end,
                          String sortBy,
                          String direction) {
        if (start < end) {
            int middle = (start + end) / 2;
            sortArray(arr, start, middle, sortBy, direction);
            sortArray(arr, middle + 1, end, sortBy, direction);
            mergeArray(arr, start, middle, end, sortBy, direction);
        }

    }


}
